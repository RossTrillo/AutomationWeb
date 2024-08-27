import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import io.cucumber.java.After;

public class StoreStepsDef {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreStepsDef() {
        // Configura el driver de Chrome
        System.setProperty("webdriver.chrome.driver", "ruta/al/chromedriver"); // Asegúrate de poner la ruta correcta
        driver = new ChromeDriver();
    }

 //   @Cuando("inicio sesión con usuario {string} y contraseña {string}")
    public void inicio_sesion_con_usuario_y_contrasena(String usuario, String contrasena) {
        driver.get("URL_DE_LA_PAGINA_LOGIN"); // Reemplaza con la URL del login

        // Espera y completa el formulario de inicio de sesión
        WebElement campoUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_CAMPOS_USUARIO")));
        WebElement campoContrasena = driver.findElement(By.id("ID_CAMPOS_CONTRASENA"));
        WebElement botonLogin = driver.findElement(By.id("ID_BOTON_LOGIN"));

        campoUsuario.sendKeys(usuario);
        campoContrasena.sendKeys(contrasena);
        botonLogin.click();

        // Espera a que se complete el inicio de sesión
        WebElement elementoPostLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_ELEMENTO_POST_LOGIN"))); // Cambia este id por uno que sea visible tras el login
        Assert.assertTrue(elementoPostLogin.isDisplayed());
    }



  //  @Entonces("debo poder ver y hacer clic en los botones y elementos en la vista")
    public void validar_y_interactuar_con_botones_y_elementos() {
        // Espera a que el botón de categoría 'Clothes' esté visible y haz clic
        WebElement botonClothes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_BOTON_CLOTHES")));
        Assert.assertTrue(botonClothes.isDisplayed());
        botonClothes.click();

        // Espera a que el botón de subcategoría 'Men' esté visible y haz clic
        WebElement botonMen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_BOTON_MEN")));
        Assert.assertTrue(botonMen.isDisplayed());
        botonMen.click();

        // Espera a que el primer producto esté visible y haz clic
        WebElement primerProducto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("SELECTOR_DEL_PRIMER_PRODUCTO")));
        Assert.assertTrue(primerProducto.isDisplayed());
        primerProducto.click();

        // Espera a que el botón para aumentar la cantidad esté visible y haz clic
        WebElement botonAumentarCantidad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_BOTON_AUMENTAR_CANTIDAD")));
        Assert.assertTrue(botonAumentarCantidad.isDisplayed());
        botonAumentarCantidad.click(); // Aumenta la cantidad

        // Espera a que el botón para añadir al carrito esté visible y haz clic
        WebElement botonAnadirCarrito = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_BOTON_ANADIR_CARRITO")));
        Assert.assertTrue(botonAnadirCarrito.isDisplayed());
        botonAnadirCarrito.click(); // Añade al carrito

        // Opcional: Verifica si el modal de confirmación está visible
        WebElement modalConfirmacion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ID_MODAL_CONFIRMACION")));
        Assert.assertTrue(modalConfirmacion.isDisplayed());
    }


}
