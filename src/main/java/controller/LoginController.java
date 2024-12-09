package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import launcher.AdminComponentFactory;
import launcher.EmployeeComponentFactory;
import launcher.LoginComponentFactory;
import model.User;
import model.validator.Notification;
import service.user.AuthenticationService;
import view.LoginView;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;

        this.loginView.addChoiceBoxListener(new RoleChoiceBoxListener());
        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class RoleChoiceBoxListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            // Check if a valid role is selected
            if ("Admin".equalsIgnoreCase(newValue) || "Employee".equalsIgnoreCase(newValue)) {
                loginView.enableLoginAndRegisterButtons(true); // Enable buttons
            } else {
                loginView.enableLoginAndRegisterButtons(false); // Disable buttons
                loginView.setActionTargetText("Please select a valid role: Admin or Employee.");
            }
        }
    }


    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            String role = loginView.getSelectedRole(); // Get role from ChoiceBox

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()) {
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("LogIn Successful!");

                if ("Admin".equalsIgnoreCase(role)) {
                    AdminComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(),
                            LoginComponentFactory.getStage());
                } else if ("Employee".equalsIgnoreCase(role)) {
                    EmployeeComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(),
                            LoginComponentFactory.getStage());
                }
            }
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            String role = loginView.getSelectedRole(); // Get role from ChoiceBox

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");

                if ("Admin".equalsIgnoreCase(role)) {
                    AdminComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(),
                            LoginComponentFactory.getStage());
                } else if ("Employee".equalsIgnoreCase(role)) {
                    EmployeeComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(),
                            LoginComponentFactory.getStage());
                }
            }
        }
    }
}
