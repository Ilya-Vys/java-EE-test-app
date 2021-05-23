package example;

import example.dto.CurrenciesFromCBR;
import example.services.CurrencyService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
@WebServlet(name = "Currency", urlPatterns = "/currency")
public class CurrencyServlet extends HttpServlet {

    private CurrencyService service = new CurrencyService();

    public CurrencyServlet() throws SQLException, ClassNotFoundException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        LocalDate localDate = date.equals("now")
                ? LocalDate.now()
                : LocalDate.parse(date);

        CurrenciesFromCBR currencies = service.getCurrencies(localDate);


        req.setAttribute("model", currencies);
        req.getRequestDispatcher("currency.jsp").forward(req, resp);

    }
}
