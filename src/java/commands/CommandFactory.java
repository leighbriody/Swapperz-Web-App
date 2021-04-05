/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

public class CommandFactory {

    public static Command createCommand(String action) {
        Command c = null;
        if (action == null) {
            action = "";
        }

        switch (action) {

            case "login":
                c = new LoginCommand();
                break;
            case "search":
                c = new SearchCommand();
                break;
            case "addToCart":
                c = new ShoppingCartCommand();
                break;

            case "uploadimage":
                c = new UploadImageCommand();
                break;
            case "logout":
                c = new LogoutCommand();
                break;
            case "register":
                c = new RegisterCommand();
                break;
            case "buyNow":
                c = new BuyNowCommand();
                break;
            case "withdrawAdd":
                c = new WithdrawAddCommand();
                break;
            case "relistAdd":
                c = new RelistAddCommand();
                break;
            case "removeAdd":
                c = new RemoveAddCommand();
                break;
            case "removeItemFromCart":
                c = new RemoveItemFromCartCommand();
                break;
            case "addBrand":
                c = new AddBrandCommand();
                break;
            case "viewUserDetails":
                //    c = new ViewUserDetailsCommand();
                break;
            case "editAdPrice":
                c = new EditAdPriceCommand();
                break;
            case "editUserDetails":
                c = new EditUserDetailsCommand();
                break;
            case "placeAd":
                c = new PlaceAdCommand();
                break;
            case "checkout":
                c = new CheckoutCommand();
                break;
            //ADMIN
            case "approvePurchase":
                c = new ApprovePurchaseCommand();
                break;
            default:
                c = new NoActionSuppliedCommand();

        }
        return c;

    }

}
