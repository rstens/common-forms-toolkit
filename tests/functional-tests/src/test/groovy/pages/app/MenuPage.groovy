package pages

class MenuPage extends BaseAppPage {

    static at = { title == 'Common Forms Toolkit' }
    static url = 'home'

    static content = {
        header_Title(wait: true) { $("h1",text: "COVID-19 Operator Screening Forms") }
        toolbar_Logout(wait: true) { $("button", "data-test": "btn-base-auth-logout") }
        agricultureScreening { $("h2", text: "Agriculture and Seafood Operator Screening") }
        minesScreening { $("h2", txt: "Mines Operator Screening") }
        forestryScreening { $("h2", txt: "Forestry Sector Operator Screening") }
    }

}
