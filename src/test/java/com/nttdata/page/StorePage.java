package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage {

    private WebDriver driver;

    // Constructor
    public StorePage(WebDriver driver) {
        this.driver = driver;
    }

    // Localizadores
    private By clothesCategory = By.xpath("//*[@id='category-3']/a");
    private By menSubcategory = By.xpath("//*[@id='left-column']/div[1]/ul/li[2]/ul/li[1]/a");
    private By firstProduct = By.xpath("//*[@id='js-product-list']/div[1]/div/article/div/div[1]/a");
    private By quantityInput = By.xpath("//*[@id='quantity_wanted']");
    private By addToCartButton = By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/button");

    // Métodos
    public void goToClothesCategory() {
        driver.findElement(clothesCategory).click();
    }

    public void goToMenSubcategory() {
        driver.findElement(menSubcategory).click();
    }

    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void setQuantity(int quantity) {
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(String.valueOf(quantity));
    }

    public void addProductToCart() {
        driver.findElement(addToCartButton).click();
    }
}
