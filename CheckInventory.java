import com.team.supplychain.dao.InventoryDAO;
import com.team.supplychain.dao.RequisitionDAO;
import com.team.supplychain.models.InventoryItem;
import com.team.supplychain.models.Requisition;
import com.team.supplychain.models.RequisitionItem;
import java.util.List;

public class CheckInventory {
    public static void main(String[] args) {
        InventoryDAO inventoryDAO = new InventoryDAO();
        RequisitionDAO requisitionDAO = new RequisitionDAO();

        System.out.println("=== INVENTORY ITEMS ===");
        List<InventoryItem> items = inventoryDAO.getAllInventoryItems();
        for (InventoryItem item : items) {
            System.out.println(item.getItemId() + ". \"" + item.getItemName() + "\" - Quantity: " + item.getQuantity());
        }

        System.out.println("\n=== RECENT APPROVED REQUISITIONS ===");
        List<Requisition> approved = requisitionDAO.getRequisitionsByStatus("Approved");
        for (Requisition req : approved) {
            System.out.println("\nRequisition: " + req.getRequisitionCode());
            if (req.getItems() != null) {
                for (RequisitionItem reqItem : req.getItems()) {
                    System.out.println("  - Item: \"" + reqItem.getItemName() + "\" Qty: " + reqItem.getQuantity());

                    // Check if it matches inventory
                    InventoryItem match = inventoryDAO.findInventoryItemByName(reqItem.getItemName());
                    if (match != null) {
                        System.out.println("    ✓ MATCHES inventory item #" + match.getItemId());
                    } else {
                        System.out.println("    ✗ NO MATCH in inventory");
                    }
                }
            }
        }
    }
}
