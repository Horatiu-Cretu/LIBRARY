package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.UserMapper;
import model.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.book.BookService;
import service.user.UserService;
import view.AdminView;
import view.model.UserDTO;

import java.io.IOException;

public class AdminController {
    private final AdminView adminView;

    public AdminController(AdminView adminView, BookService bookService, UserService userService) {
        this.adminView = adminView;
        this.adminView.addReportButtonListener(new ReportButtonListener());
    }

    private class ReportButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            UserDTO selectedUser = adminView.getSelectedUser();
            if (selectedUser == null) {
                adminView.addDisplayAlertMessage("Error",
                        "Report error",
                        "No employee selected for the report!");
                return;
            }

            User user = UserMapper.convertUserDTOToUser(selectedUser);
            String employeeName = user.getUsername();
            Long employeeId = user.getId();

            String pdfFilePath = "EmployeeReport.pdf";
            try (PDDocument document = new PDDocument()) {
                // Create a new page
                PDPage page = new PDPage();
                document.addPage(page);

                // Write content to the page
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.setLeading(14.5f);
                    contentStream.newLineAtOffset(25, 750);

                    // Add content
                    contentStream.showText("Employee Report");
                    contentStream.newLine();
                    contentStream.newLine();
                    contentStream.showText("Employee Name: " + employeeName);
                    contentStream.newLine();
                    contentStream.showText("Employee ID: " + employeeId);

                    contentStream.endText();
                }

                // Save the document to the specified file path
                document.save(pdfFilePath);

                adminView.displayAlertMessage("PDF report generated successfully",
                        "File Location: " + pdfFilePath,
                        "The report was saved successfully.");
            } catch (IOException e) {
                adminView.displayAlertMessage("ERROR AT GENERATING REPORT",
                        "File Location: " + pdfFilePath,
                        "Could not generate the report.");
            }
        }
    }
}
