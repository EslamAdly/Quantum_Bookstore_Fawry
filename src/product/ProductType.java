package product;

public class ProductType {
    private String name;
    private boolean requiredShipping;
    private boolean isSalable;

    public ProductType(String name, boolean requiredShipping, boolean isSalable) {
        this.name = name;
        this.requiredShipping = requiredShipping;
        this.isSalable = isSalable;
    }

    public String getName() {
        return name;
    }

    public boolean isRequiredShipping() {
        return requiredShipping;
    }

    public boolean isSalable() {
        return isSalable;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "name='" + name + '\'' +
                ", requiredShipping=" + requiredShipping +
                ", isSalable=" + isSalable +
                '}';
    }
}
