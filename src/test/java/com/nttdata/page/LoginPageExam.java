package com.nttdata.page;

import org.openqa.selenium.By;

public class LoginPageExam {

    // Localizador para el campo de correo electrónico
    public static By emailInput = By.xpath("//*[@id='field-email']");

    // Localizador para el campo de contraseña
    public static By passwordInput = By.xpath("//*[@id='field-password']");

    // Localizador para el botón de inicio de sesión
    public static By loginButton = By.id("submit-login");

    public static By clothesCategory = By.xpath("//*[@id='category-3']/a");
    public static By menSubcategory = By.xpath("//*[@id='left-column']/div[1]/ul/li[2]/ul/li[1]/a");
    public static By firstProduct = By.xpath("//*[@id='js-product-list']/div[1]/div/article/div/div[1]/a");
    public static By quantityInput = By.xpath("//*[@id='quantity_wanted']");
    public static By addToCartButton = By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/button");
    public static By increaseQuantityButton = By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[1]/div/span[3]/button[1]");

    //
    public static By confirmationPopup = By.cssSelector(".material-icons.rtl-no-flip");
    // Localizador para el precio del producto en el popup
    public static By productPricePopup = By.xpath("//*[@id='main']/div[1]/div[2]/div[1]/div[2]/div/span[1]");

    // Localizador para el monto total en el popup
    public static By totalAmountPopup = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/p[4]/span[2]");

    public static By productQuantityPopup = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[1]/div/div[2]/span[3]/strong");


    // Localizador para el botón "Finalizar Compra"
    public static By finalizarCompraButton = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a");

    // Localizador para el título de la página del carrito
    public static By carritoPageTitle = By.xpath("//*[@id='main']/div/div[1]/div/div[1]/h1");

    // Localizador para el precio del producto en el carrito
    public static By productPriceInCart = By.xpath("//*[@id='main']/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");

    // Localizador para el monto total en el carrito
    public static By totalAmountInCart = By.xpath("//*[@id='main']/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[2]/span/strong");


}
