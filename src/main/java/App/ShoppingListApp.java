package App;
import App.Domain.Category;
import App.Domain.Product;
import App.Service.ProductService;
import java.math.BigDecimal;
import java.util.Scanner;
import static App.Domain.Category.FRUITS;

public class ShoppingListApp {
    private static ProductService productService;

    ShoppingListApp (ProductService productService) {
        this.productService = productService;
    }

    public void execute() {
        inputExamples();
        boolean exit = false;
        while(!exit) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Find all products");
                System.out.println("4. Update product");
                System.out.println("5. Delete product by ID");
                System.out.println("0. Exit");
                int userInput = Integer.parseInt(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findById();
                        break;
                    case 3:
                        findAllProducts();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;
                    case 0:
                        exit = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void inputExamples() {
        Product one = new Product();
        one.setCategory(FRUITS);
        one.setName("First");
        one.setPrice(new BigDecimal(123));
        one.setDiscount(new BigDecimal(20));
        one.setDescription("first product of the list");
        Long id=productService.createProduct(one);
        System.out.println(id);
        Product two = new Product();
        two.setCategory(FRUITS);
        two.setName("Second");
        two.setPrice(new BigDecimal(23));
        two.setDiscount(new BigDecimal(50));
        two.setDescription("second product of the list");
        Long id2=productService.createProduct(two);
        System.out.println(id2);
    }
    private static void createProduct(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter category");
        Category category= Category.valueOf(scanner.nextLine());
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("Enter discount");
        BigDecimal discount = new BigDecimal(scanner.nextLine());
        System.out.println("Enter description");
        String description = scanner.nextLine();

        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);
        Long id=productService.createProduct(product);
        System.out.println("Result: " + id);

    }

    public void update(){
        Scanner scanner=new Scanner(System.in);
        Long id=findById();
        System.out.println("Enter category");
        Category category= Category.valueOf(scanner.nextLine());
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("Enter discount");
        BigDecimal discount = new BigDecimal(scanner.nextLine());
        System.out.println("Enter description");
        String description = scanner.nextLine();
        Product product=new Product();
        product.setId(id);
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);
        productService.updateProduct(product);
    }
    public void delete(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter id to delete product");
        Long id=scanner.nextLong();
        productService.delete(id);
    }
    public Long findById(){
        System.out.println("Enter product id: ");
        Scanner scanner=new Scanner(System.in);
        long id = scanner.nextLong();
        Product findProductResult = productService.findById(id);
        System.out.println(findProductResult);
        return findProductResult.getId();
    }
    public void findAllProducts() {
        System.out.println(productService.findAllProducts());
    }
}
