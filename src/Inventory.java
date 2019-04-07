
public class Inventory {

    public InventoryItem[] items;
    public int storageSpace;

    public Inventory(int myCapacity) {
        storageSpace = myCapacity;
        items = new InventoryItem[storageSpace];
        for (int i = 0; i < storageSpace; i++) {
            clearSlot(i);
        }
    }

    public void clearSlot(int whichSlot) {
        items[whichSlot] = null;
    }

    public boolean hasSpace() {
        for (int i = 0; i < storageSpace; i++) {
            if (items[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void addItem(InventoryItem addedItem) {
        for (int i = 0; i < storageSpace; i++) {
            if (items[i] == null) {
                items[i] = addedItem;
                break;
            }
        }

    }

    public int searchFor(String searchedItem) {
        for (int i = 0; i < storageSpace; i++) {
            if (items[i] != null) {
                if (items[i].itemName == searchedItem) {
                    return i;
                }
            }
        }
        return -1;
    }

}
