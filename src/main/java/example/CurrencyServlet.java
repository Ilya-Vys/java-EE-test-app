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


@WebServlet(name = "Currency", urlPatterns = "/currency")
public class CurrencyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        LocalDate localDate = date.equals("now")
                ? LocalDate.now()
                : LocalDate.parse(date);

        Map<LocalDate, CurrencyRate> base = CurrencyDB.getInstance().getBase();

        CurrencyRate rate = localDate == null
                ? base.get(LocalDate.now())
                : base.get(localDate);

        req.setAttribute("model", rate);
        req.getRequestDispatcher("currency.jsp").forward(req, resp);

    }
}
