package example;

import example.CurrencyDB;
import example.CurrencyRate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;


@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrencyDB db = CurrencyDB.getInstance();
        Map<LocalDate, CurrencyRate> rates = db.getBase();
        String date = req.getParameter("date");
        LocalDate localDate = LocalDate.parse(date);
        CurrencyRate rate = rates.get(localDate);

        req.setAttribute("model", rate);
        req.getRequestDispatcher("currency.jsp").forward(req, resp);

    }
}
