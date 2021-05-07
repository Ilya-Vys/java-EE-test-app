package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "Currency", urlPatterns = "/currency")
public class CurrencyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        LocalDate localDate = date.equals("now")
                ? LocalDate.now()
                : LocalDate.parse(date);


        CurrenciesFromCBR currencies = new CurrenciesFromCBR(localDate).getCurrencies();
        currencies.getRates().forEach(c -> System.out.println(c.getName()));

        req.setAttribute("model", currencies);
        req.getRequestDispatcher("currency.jsp").forward(req, resp);

    }
}
