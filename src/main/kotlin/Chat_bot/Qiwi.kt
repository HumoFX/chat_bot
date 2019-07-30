package Chat_bot

import com.qiwi.billpayments.sdk.client.BillPaymentClient
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory
import com.qiwi.billpayments.sdk.model.`in`.PaymentInfo
import java.util.UUID
import java.util.Currency
import java.math.BigDecimal
import com.qiwi.billpayments.sdk.model.MoneyAmount



class Qiwi {
    //secretKey надо глянуть String secretKey = "eyJ2ZXJzaW9uIjoicmVzdF92MyIsImRhdGEiOnsibWVyY2hhbnRfaWQiOjUyNjgxMiwiYXBpX3VzZXJfaWQiOjcxNjI2MTk3LCJzZWNyZXQiOiJmZjBiZmJiM2UxYzc0MjY3YjIyZDIzOGYzMDBkNDhlYjhiNTnONPININONPN090MTg5Z**********************";


    open fun payment(): String? {
        var secretKey = "145f60829bcabcb15a1cabdf6f7932d2"
        var client: BillPaymentClient = BillPaymentClientFactory.createDefault(secretKey)
        var amount = MoneyAmount(
                BigDecimal.valueOf(499.90),
                Currency.getInstance("RUB")

        )
        var billId = UUID.randomUUID().toString()
        var successUrl = "https://merchant.com/payment/success?billId=893794793973"
        var paymentUrl = client.createPaymentForm(PaymentInfo(secretKey, amount, billId, successUrl))
        return paymentUrl
    }
}