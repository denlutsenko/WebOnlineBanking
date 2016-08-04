package ua.lutsenko.banking.filter;
/**
 * Created by Denis Lutsenko.
 */

/**
 * enum class contains all url path.
 */
enum UrlsStorageUtil {
    ADMIN_PAGE_CABINET("/jsp/adminPages/adminPersonalPage.jsp"),
    ADMIN_PAGE_APPLICATION_LIST("/jsp/adminPages/applicationList.jsp"),
    ADMIN_PAGE_NEW_ACCOUNT("/jsp/adminPages/applyNewAccount.jsp"),
    ADMIN_PAGE_BLOCKED_ACCOUNTS("/jsp/adminPages/blockedAccounts.jsp"),
    ADMIN_URL_BLOCKED_ACCOUNT("/bank24/blockedAccounts"),
    ADMIN_URL_APPLICATIONS("/bank24/newApplications"),
    ADMIN_URL_NEW_ACCOUNT("/bank24/createNewAccountForm"),
    USER_PAGE_ACCOUNTS("/jsp/accountPages/accounts.jsp"),
    USER_PAGE_CHARGE_ACCOUNT("/jsp/accountPages/chargeAccount.jsp"),
    USER_PAGE_LOCK_CARD_FORM("/jsp/accountPages/blockCardForm.jsp"),
    USER_PAGE_MANAGEMENT("/jsp/accountPages/management.jsp"),
    USER_PAGE_NEWS("/jsp/accountPages/news.jsp"),
    USER_PAGE_ADDRESS_FORM("/jsp/addressPages/addressForm.jsp"),
    USER_PAGE_APPLICATION_FORM("/jsp/applicationForm/applicationForm.jsp"),
    USER_PAGE_CURRENT_OPERATION_HISTORY("/jsp/operationPages/currentOperationsHistory.jsp"),
    USER_PAGE_OPERATION_PAGE("/jsp/operationPages/operationPage.jsp"),
    USER_PAGE_OPERATION_HISTORY("/jsp/operationPages/operationsHistory.jsp"),
    USER_PAGE_ERROR_PAGE("/jsp/reportPages/errorPage.jsp"),
    USER_PAGE_PERSONAL_PAGE("/jsp/userPages/personalPage.jsp"),
    USER_PAGE_LAYOUT_ADMIN_MENU("/jsp/layout/adminMenu.jsp"),
    USER_PAGE_LAYOUT_HEADER("/jsp/layout/header.jsp"),
    USER_PAGE_LAYOUT_USER_MENU("/jsp/layout/userMenu.jsp"),
    USER_URL_ADDR_FORM("/bank24/managements/openingNewAccount/addressForm"),
    USER_URL_ACCOUNTS("/bank24/allActiveAccounts"),
    USER_URL_BLOCK_ACCOUNT("/bank24/managements/blockAccount"),
    USER_URL_HISTORY("/bank24/currentOperationsHistory"),
    USER_URL_OPEN_ACCOUNT("/bank24/managements/openingNewAccount/form"),
    USER_URL_PAYMENT("/bank24/PaymentOperations"),
    USER_REFILL_BALANCE("/bank24/managements/refillBalance");



    private String path;

    UrlsStorageUtil(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}