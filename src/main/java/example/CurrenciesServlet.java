package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrencyDB db = CurrencyDB.getInstance();
        Map<LocalDate, CurrencyRate> rates = db.getBase();
        Collection<String> localDates = rates.keySet().stream()
                .map(LocalDate::toString)
                .collect(Collectors.toList());


        req.setAttribute("model", localDates);
        req.getRequestDispatcher("currencies.jsp").forward(req, resp);
    }
}
