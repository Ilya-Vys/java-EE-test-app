package example;

import example.dto.CurrenciesFromCBR;
import example.dto.parsers.ParserWithJSoup;
import example.dto.parsers.ParserWithJackson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "Currency", urlPatterns = "/currency")
public class CurrencyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        LocalDate localDate = date.equals("now")
                ? LocalDate.now()
                : LocalDate.parse(date);




        CurrenciesFromCBR currencies = new CurrenciesFromCBR(localDate,
                "http://www.cbr.ru/scripts/XML_daily.asp?date_req=", new ParserWithJackson()).getCurrencies2();

        req.setAttribute("model", currencies);
        req.getRequestDispatcher("currency.jsp").forward(req, resp);

    }
}
