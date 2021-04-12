package by.tc.shop.controller.command;

import by.tc.shop.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.AUTHORIZATION, new Authorization());
        commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPage());
        commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
        commands.put(CommandName.GOTOAUTHORIZATIONPAGE, new GoToAuthorizationPage());
        commands.put(CommandName.CHANGELOCALE, new ChangeLocale());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GOTOCARTPAGE, new GoToCartPage());
        commands.put(CommandName.GOTOCATEGORYPAGE, new GoToCategoryPage());
        commands.put(CommandName.SAVENEWORDER, new SaveNewOrder());
        commands.put(CommandName.EDITPRODUCTAMOUNT, new EditProductAmount());
        commands.put(CommandName.DELETEORDERITEM, new DeleteOrderItem());
        commands.put(CommandName.CONFIRMORDER, new ConfirmOrder());
        commands.put(CommandName.SAVEUSERDETAILS, new SaveUserDetails());
        commands.put(CommandName.GOTOACTIVEORDERSPAGE, new GoToActiveOrdersPage());
        commands.put(CommandName.GOTOACTIVEORDERSINFOPAGE, new GoToActiveOrdersInfoPage());
        commands.put(CommandName.VIEWALLCLIENTS, new ViewAllClients());
        commands.put(CommandName.VIEWUSERDETAILS, new ViewUserDetails());
        commands.put(CommandName.BANUSER, new BanUser());
        commands.put(CommandName.DELETEORDER, new DeleteOrder());
        commands.put(CommandName.VIEWBANNEDCLIENTS, new ViewBannedClients());
        commands.put(CommandName.UNBANUSER, new UnbanUser());
        commands.put(CommandName.UPDATEORDERREADYDATE, new UpdateOrderReadyDate());
        commands.put(CommandName.VIEWORDERSBYSTATUS, new ViewOrdersByStatus());
        commands.put(CommandName.GOTOADDPRODUCTPAGE, new GoToAddProductPage());
        commands.put(CommandName.ADDPRODUCT, new AddProduct());
        commands.put(CommandName.GOTOUSERDETAILSPAGE, new GoToUserDetailsPage());
        commands.put(CommandName.DELETEPRODUCT, new DeleteProduct());
        commands.put(CommandName.GOTODELIVERYPAGE, new GoToDeliveryPage());
        commands.put(CommandName.GOTODELIVERYNUMBERPAGE, new GoToDeliveryNumberPage());
        commands.put(CommandName.SAVEDELIVERYITEM, new SaveDeliveryItem());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}
