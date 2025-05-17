package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Db.BillDAO;
import Db.DBConnection;
import Db.OrderItemDAO;
import Db.ProductDAO;
import Model.Bill;
import Model.OrderItem;
import Model.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BillController {
    private BillDAO billDAO;
    private OrderItemDAO orderItemDAO;
    private ProductDAO productDAO;


    public BillController() {
        billDAO = new BillDAO();
        orderItemDAO = new OrderItemDAO();
        productDAO = new ProductDAO();
    }

    public Bill getBillById(Integer id) {
        if (id == null) {
            return null;
        }
        try {
            return billDAO.getBillById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bill> getBills() {
        try {
            List<Bill> billList = billDAO.getAllBills();
            return billList != null ? billList : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Bill> getBillsByEmployeeId(Integer employeeId) {
        if (employeeId == null) {
            return Collections.emptyList();
        }
        try {
            List<Bill> billList = billDAO.getBillsByEmployeeId(employeeId);
            return billList != null ? billList : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public String placeOrder(Integer employeeId, List<OrderItem> orderItems) throws SQLException {
        if (employeeId == null) {
            return "Error: Employee ID cannot be null";
        }
        if (orderItems == null || orderItems.isEmpty()) {
            return "Error: Order items cannot be empty";
        }

        for (OrderItem item : orderItems) {
            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                return "Error: Quantity must be greater than 0";
            }
            if (item.getPrice() == null || item.getPrice() < 0) {
                return "Error: Price cannot be negative";
            }
            Product product = productDAO.getProductById(item.getProductId() != null ? item.getProductId() : -1);
            if (product == null) {
                return "Error: Product with ID " + item.getProductId() + " does not exist";
            }
        }

        double totalPrice = orderItems.stream()
                .mapToDouble(item -> {
                    Integer quantity = item.getQuantity();
                    Double price = item.getPrice();
                    return (quantity != null ? quantity : 0) * (price != null ? price : 0.0);
                })
                .sum();

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            Bill bill = new Bill(null, "", totalPrice, null, employeeId);
            int billId = billDAO.createBill(bill);

            for (OrderItem item : orderItems) {
                item.setInvoiceId(billId);
                orderItemDAO.createOrderItem(item);
            }

            conn.commit();
            return "Order placed successfully. Bill ID: " + billId + ", Total Price: $" + totalPrice;
        } catch (SQLException e) {
            try {
                Connection conn = DBConnection.getConnection();
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return "Error: Failed to place order - " + e.getMessage();
        }
    }

    public boolean updateBill(Bill bill) {
        if (bill == null || bill.getId() == null) {
            return false;
        }
        try {
            billDAO.updateBill(bill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBill(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            billDAO.deleteBill(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bill> searchBillsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return Collections.emptyList();
        }
        try {
            List<Bill> billList = billDAO.searchBillsByDateRange(startDate, endDate);
            return billList != null ? billList : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public OrderItem getOrderItemById(Integer id) {
        if (id == null) {
            return null;
        }
        try {
            return orderItemDAO.getOrderItemById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderItem> getOrderItems() {
        try {
            List<OrderItem> itemList = orderItemDAO.getAllOrderItems();
            return itemList != null ? itemList : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<OrderItem> getOrderItemsByBillId(Integer billId) {
        if (billId == null) {
            return Collections.emptyList();
        }
        try {
            List<OrderItem> itemList = orderItemDAO.getOrderItemsByBillId(billId);
            return itemList != null ? itemList : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean updateOrderItem(OrderItem item) {
        if (item == null || item.getId() == null) {
            return false;
        }
        try {
            orderItemDAO.updateOrderItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderItem(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            orderItemDAO.deleteOrderItem(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}