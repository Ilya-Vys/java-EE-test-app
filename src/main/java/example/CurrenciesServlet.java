package example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;


@WebServlet(name = "Currencies", urlPatterns = "/currencies")
public class CurrenciesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("currencies.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        LocalDate localDate = LocalDate.parse(date);
        Map<LocalDate, CurrencyRate> map = CurrencyDB.getInstance().getBase();
        CurrencyRate rate = map.containsKey(localDate)
                ? map.get(localDate)
                : map.get(LocalDate.now());
        req.setAttribute("model", rate);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("currency.jsp");
        requestDispatcher.forward(req, resp);
    }
}
