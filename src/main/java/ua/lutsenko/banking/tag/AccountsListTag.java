package ua.lutsenko.banking.tag;

import ua.lutsenko.banking.entity.Condition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

/**
 * Created by Denis Lutsenko.
 */

/**
 * Personal tag class. Methods of this class builds table to show clients account data.
 */
public class AccountsListTag extends TagSupport {
    private Condition accountRootElem;
    private String lang;

    public void setAccountRootElem(Condition accountRootElem) {
        this.accountRootElem = accountRootElem;
    }

    public void setLang(String locale) {
        if (locale.equals("ru_RU")) {
            this.lang = "language_ru";
        } else {
            this.lang = "language_en";
        }
    }

    @Override
    public int doStartTag() throws JspException {
        ResourceBundle rb = ResourceBundle.getBundle(lang);
        String currentBalance = rb.getString("CURRENT_BALANCE");
        String withDrawlPercent = rb.getString("CURRENT_BALANCE");
        String monthlyPercent = rb.getString("MONTHLY_PERCENT");
        try {
            JspWriter out = pageContext.getOut();
            out.write("<div><b> " + accountRootElem.getType() + accountRootElem.getAccount().getAccountCode() + "</b></div>");
            out.write("<div>" + currentBalance + accountRootElem.getAccount().getCurrentBalance() +
                    accountRootElem.getAccount().getCurrency() + "</div>");
            out.write("<div>" + withDrawlPercent + accountRootElem.getPercentOfWithdrawal() + "%</div>");
            out.write("<div>" + monthlyPercent + accountRootElem.getMonthlyPercent() + "%</div>");
            out.write("<br/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
