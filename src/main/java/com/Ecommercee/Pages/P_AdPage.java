package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.logs.logsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class P_AdPage {

    private final GUIDriver driver;

    private final By adCloseButton = By.cssSelector(".ad-close");

    public P_AdPage(GUIDriver driver) {
        this.driver = driver;
    }

    @Step("Handle advertisement if present")
    public P_AdPage handleAdIfPresent() {

        try {

            if (!driver.element().findElements(adCloseButton).isEmpty()) {
                driver.element().click(adCloseButton);
                logsManager.info("Ad closed successfully");
            } else {
                logsManager.info("No ad displayed");
            }

        } catch (Exception e) {
            logsManager.info("Ad not found or already closed");
        }

        return this;
    }
}
