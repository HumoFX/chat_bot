package Chat_bot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.text.SimpleDateFormat
import java.util.*
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import java.io.*
import kotlin.collections.ArrayList

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import java.util.Comparator
import com.google.common.graph.ElementOrder.sorted
import com.google.common.graph.ElementOrder.sorted
//import org.junit.internal.matchers.ThrowableMessageMatcher.hasMessage






// 🇷🇺🔄🇺🇿






class Bot : TelegramLongPollingBot() {
    var qiwi = Qiwi()
    var abc = PostgreSQLJDBC()
    var file = Spam()
    val partner_searchM = arrayListOf<Int>()
    val partner_searchJ = arrayListOf<Int>()
    val partner_searchMU = arrayListOf<Int>()
    val partner_searchJU = arrayListOf<Int>()
    val partner_searchME = arrayListOf<Int>()
    val partner_searchJE = arrayListOf<Int>()
    val connected_userM = arrayListOf<Long>()
    val connected_userJ = arrayListOf<Long>()
  //  var payment_data: Array<Array<String>> = Array(100, { Array(4, {"0"}) })
    var users : MutableMap<Long,String> = hashMapOf()
    var users_pos : MutableMap<Long,Int> = hashMapOf()
    var profile : MutableMap<Long,String> = hashMapOf()
    var user_lang : MutableMap<Long,Int> = hashMapOf()
    var file_nameM : MutableMap<Long,Int> = hashMapOf()
    var file_nameJ : MutableMap<Long,Int> = hashMapOf()
    var user_banned : MutableMap<Long,Int> = hashMapOf()
    var user_referals : MutableMap<Long,Int> = hashMapOf()
    var user_registor: MutableMap<Long,Int> = hashMapOf()
    var user_ostatok: MutableMap<Long,Int> = hashMapOf()
    var user_balance: MutableMap<Long,Int> = hashMapOf()
    var user_qiwi: MutableMap<Long,String> = hashMapOf()
    var file_iterator = 0
    var male_size = 0
    var female_size = 0
    var database = abc.read()
    var database_search = abc.read_search()
    var database_connected = abc.read_conn()
    var emojies = arrayListOf<String>()
    var language = arrayListOf<String>()
    var test1 =0
    var test2 =0
    var emoji_change = 0
  //  var h = abc.CREATE()
  //  var h1 = abc.CREATE1()
    var users_map: MutableMap<Long,Array<String>> = hashMapOf()
    var spam_iterator = arrayListOf<Int>(1000)
    val database_size = database[0][2].toInt()
    val database_search_size = database_search[0][2].toInt()
    val database_connected_size = database_connected[0][2].toInt()
    var all_size = database_size
    var file_id = ""
   // val users_connection :MutableMap<Long,String>=
    override fun getBotToken(): String {
        return "639145492:AAHVTi5VaxKzxUWa8C65a2ubMUpXmrTfF20"
    }

    override fun getBotUsername(): String {
        return "Hum0bot"
    }

    override fun onUpdateReceived(update: Update?) {
        if(database_size!=0)
        {
            var i=0
            while (i<database_size){
                if(users.contains(database[i][0].toLong()))
                {

                }
                else
                {
                    if(database[i][1]=="male")
                    {
                        male_size++
                    }
                    else
                    {
                        female_size++
                    }
                    users.put(database[i][0].toLong(), database[i][1])
                    users_pos.put(database[i][0].toLong(),database[i][3].toInt())
                    user_lang.put(database[i][0].toLong(),database[i][5].toInt())
                    profile.put(database[i][0].toLong(),database[i][4])
                    user_banned.put(database[i][0].toLong(),database[i][6].toInt())
                    user_referals.put(database[i][0].toLong(),database[i][7].toInt())
                    users_map.put(database[i][0].toLong(),database[i])
                    user_balance.put(database[i][0].toLong(),database[i][8].toInt())
                }


                println("SIZE: ${users.size}")
                i++
            }
        }

        if(database_search_size!=0&&test1==0)
        {
            var i =0
            while(i<database_search_size)
            {
                var lang = user_lang.get(database_search[i][0].toLong())
                if(database_search[i][1]=="male")
                {
                    if (lang==0)
                    {
                        partner_searchM.add(database_search[i][0].toInt())
                    }
                    if (lang==1)
                    {
                        partner_searchMU.add(database_search[i][0].toInt())
                    }
                    if (lang==2)
                    {
                        partner_searchME.add(database_search[i][0].toInt())
                    }

                }
                else
                {
                    if (lang==0)
                    {
                        partner_searchJ.add(database_search[i][0].toInt())
                    }
                    if (lang==1)
                    {
                        partner_searchJU.add(database_search[i][0].toInt())
                    }
                    if (lang==2)
                    {
                        partner_searchJE.add(database_search[i][0].toInt())
                    }

                }
                i++
            }
            test1 = 1
            println("ok ")
        }
        if(database_connected_size!=0&&test2==0)
        {
            var i =0
            while(i<database_connected_size)
            {
                if(database_connected[i][1]=="male")
                {
                    connected_userM.add(database_connected[i][0].toLong())
                }
                else {
                    connected_userJ.add(database_connected[i][0].toLong())
                }
                i++
            }
            println("sok ")
            test2=1
        }

       println("ALL USERS :")
        println(" ${users.keys} - ${users.values} \n")


        emojies.add("\uD83D\uDE0E")
        emojies.add("\uD83E\uDD13")
        emojies.add("\uD83D\uDE02")
        emojies.add("\uD83D\uDE43")
        emojies.add("\uD83D\uDC7D")
        emojies.add("\uD83D\uDCA9")
        emojies.add("\uD83D\uDC68\uD83C\uDFFB")
        emojies.add("\uD83D\uDC71\uD83C\uDFFB\u200D♂")
        emojies.add("\uD83E\uDD34\uD83C\uDFFB")
        emojies.add("\uD83D\uDC68\uD83C\uDFFB\u200D\uD83C\uDFA4")
        emojies.add("\uD83E\uDDB8\uD83C\uDFFB\u200D♂")
        emojies.add("\uD83D\uDC73\uD83C\uDFFB\u200D♂")
        emojies.add("☺")
        emojies.add("️\uD83E\uDD70")
        emojies.add("\uD83D\uDE0F")
        emojies.add("\uD83D\uDE1D")
        emojies.add("\uD83D\uDC69\uD83C\uDFFB\u200D\uD83E\uDDB1")
        emojies.add("\uD83D\uDC71\uD83C\uDFFB\u200D♀")
        emojies.add("\uD83D\uDC69\uD83C\uDFFB")
        emojies.add("\uD83E\uDDB8\uD83C\uDFFB\u200D♀")
        emojies.add("\uD83D\uDC78")
        emojies.add("\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFA4")
        emojies.add("\uD83D\uDC67\uD83C\uDFFB")
        emojies.add("\uD83D\uDE08")
        language.add("\uD83C\uDDF7\uD83C\uDDFA")
        language.add("\uD83C\uDDFA\uD83C\uDDFF")
        language.add("\uD83C\uDDEC\uD83C\uDDE7")
        if (update != null) {
            if (update.hasMessage() && update.message.hasPhoto()) {
                // Message contains photo
                // Set variables
                val chat_id = update.message.chatId

                val photos = update.message.photo
                if(chat_id.toInt() == 299384140) {
                    file_id = photos.stream()
                            //  .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).fileId
                }
                val f_width = photos.stream()
                       // .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).width!!
                val f_height = photos.stream()
                        //.sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).height!!
 /*               val caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height)
                val msg = SendPhoto()
                        .setChatId(chat_id)
                        .setPhoto(f_id)
                        .setCaption(caption)// в caption записываем описание фото!(доработать задержку)
                try {
                    execute(msg) // Call method to send the message
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }*/

            }
            if (update.hasMessage() && update.message.hasText()) {

                val user_id = update.message.chatId
                var message_text = update.message.text
                var message_id = update.message.messageId

                var user_firstname = update.message.from.firstName
                var user_username:String
                try{
                    user_username = update.message.from.userName
                    val user_username_:String? = update.message.chat.userName
                }catch(e :IllegalStateException ){
                    //e.printStackTrace()
                    user_username = "null"
                }

                val user_first_name = update.message.chat.firstName
                val answer = message_text

               // if (update.hasMessage() && update.message.hasText()) {
                //    val message_text = update.message.text
                    val chat_id = update.message.chatId
                    if (update.message.text.startsWith("/start")) {
                        if(users.contains(user_id))
                        {
                            var lang = user_lang.get(user_id)
                            var text = "*Вы уже прошли регистрацию * \n" +
                                       " _Можете переходить к общению_ "
                            if(lang!=null) {
                                if (lang == 0)
                                {

                                }
                                if (lang == 1)
                                {
                                    text = "*Сиз регистрациядан откансиз* \n _Мулокотга отишингиз мумкин_"
                                }
                                if (lang == 2)
                                {
                                    text = "*You have been registered* \n _You can start communication_"
                                }
                            }

                            val message = SendMessage() // Create a message object object
                                    .setChatId(chat_id)
                                    .setParseMode("Markdown")
                                    .setText("$text")
                            try {
                                execute(message) // Sending our message object to user
                            } catch (e: TelegramApiException) {
                                e.printStackTrace()
                            }
                            join(user_id)

                        }
                        else
                        {
                            language(user_id)
                            parse_link(user_id,message_text)
                        }

                    } else {

                    }



                val tmp = users.get(user_id)
                val tmp2 = users_pos.get(user_id)
                val tmp3 = user_banned.get(user_id)
                if(tmp3==1)
                {
                    var text = "ВЫ ЗАБАНЕНЫ!"
                    var lang = user_lang.get(user_id)
                    if(lang!=null)
                    {
                        if(lang==0)
                        {

                        }
                        if(lang==1)
                        {
                            text="Proflingiz BANlangan!"
                        }
                        if(lang==2)
                        {
                            text = "YOU WERE BANNED"
                        }
                        val message = SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setParseMode("Markdown")
                                .setText("$text")
                        try {
                            execute(message) // Sending our message object to user
                        } catch (e: TelegramApiException) {
                            e.printStackTrace()
                        }
                    }
                }
                else {
                    if (tmp == "male" || tmp == "female") {
                        if (tmp2 == 0)
                        {
                            button_group_0(message_text, user_id)
                            if (user_id.toInt() == 299384140) {
                                button_group_4(message_text, user_id)
                            }
                            //           users_pos.remove(user_id)
                        }
                        if (tmp2 == 1) {
                            button_group_1(message_text, user_id)
                            //            users_pos.remove(user_id)

                        }
                        if (tmp2 == 2) {
                            button_group_2(message_text, user_id)
                            //          users_pos.remove(user_id)
                        }
                        if (tmp2 == 3) {
                            ban(user_id, message_text)
                        }
                        if (tmp2 == 4) {
                            unban(user_id, message_text)
                        }
                        if (tmp2 == 5) {
                            warning(user_id, message_text)
                        }
                        if (tmp2 == 6) {
                            save_qiwi(user_id,message_text)
                        }
                        if (tmp2 == 7) {
                            get_balance(user_id,message_text)
                            button_group_5(message_text, user_id)
                        }
                        if (tmp2 == 8) {
                            button_group_5(message_text, user_id)
                        }
                        if (tmp2 == 9) {
                            posting(user_id,file_id,message_text)
                            file_id=""
                        }
                        if (tmp2 == 10) {
                            button_group_5(message_text, user_id)
                        }
                        println("userposremove ${users_pos.keys}  ${users_pos.values}")
                        connection(user_id, message_text)
                        connected(user_id, message_text, user_firstname)
                    }
                }

                log(
                        user_first_name,
                        user_username,
                        user_id.toString(),
                        message_text,
                        answer
                )

            }



            if (update.hasCallbackQuery()) {
                // Set variables

                val call_data = update.callbackQuery.data
                val chat_id = update.callbackQuery.message.chatId
                val message_text = update.callbackQuery.message.text
                val message_id = update.callbackQuery.message.messageId
                if (call_data == "male")
                {
                    if(users.contains(chat_id))
                    {
                    val message = SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setParseMode("Markdown")
                            .setText("_Можете переходить к общению_")
                    try
                        {
                        execute(message) // Sending our message object to user
                        } catch (e: TelegramApiException)
                        {
                        e.printStackTrace()
                        }
                    }
                    else
                    {
                        male_size++
                        all_size++
                        println("UV : ${users.values}  UK : ${users.keys}")
                    }

                    users.put(chat_id ,"male" )

                        user_referals.put(chat_id, 0)
                        profile_emojiM(chat_id)


                    val new_message = DeleteMessage()
                            .setChatId(chat_id)
                            .setMessageId(message_id.toInt())
                    try {
                        execute(new_message)
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }
                    val answer = AnswerCallbackQuery()
                    answer.callbackQueryId = update.callbackQuery.id
                    answer.text = "Принято!!"
                    answer.showAlert = false
                    try {
                        execute(answer)
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }

                }
                if (call_data == "female")
                {
                    if(users.contains(chat_id)) {
                        val message = SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setParseMode("Markdown")
                                .setText("_Можете переходить к общению_ ")
                        try {

                            execute(message) // Sending our message object to user
                        } catch (e: TelegramApiException) {
                            e.printStackTrace()
                        }
                    }
                    else
                    {
                        female_size++
                        all_size++
                        println("UV : ${users.values}  UK : ${users.keys}")
                    }
                    users.put(chat_id,"female")
                    user_referals.put(chat_id,0)
                    profile_emojiJ(chat_id)
                    val new_message = DeleteMessage()
                            .setChatId(chat_id)
                            .setMessageId(message_id.toInt())
                    try {
                        execute(new_message)
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }
                    val answer = AnswerCallbackQuery()
                    answer.callbackQueryId = update.callbackQuery.id
                    answer.text = "Принято!!"
                    answer.showAlert = false
                    try {
                        execute(answer)
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }

                }
                else if(call_data=="rub")
                {
                    users_pos.replace(chat_id,6)
                    var text = "Введите свой Qiwi кошелек( c + ).Пример: +998901234567"
                    var text1 = "У вас недостаточно средств на балансе"
                    var lng = user_lang.get(chat_id)
                    if(lng==1)
                    {

                    }
                    if(lng==2)
                    {

                    }
                    var user = user_balance.get(chat_id)
                    if(user!=null)
                    {
                        if (user >= 30) {
                            val new_message = SendMessage()
                                    .setChatId(chat_id)
                                    .setText("$text")
                            try {
                                execute(new_message)
                            } catch (e: TelegramApiException) {
                                e.printStackTrace()
                            }
                            users_pos.replace(chat_id,6)

                        }
                    }
                    else
                    {
                            val answer = AnswerCallbackQuery()
                            answer.callbackQueryId = update.callbackQuery.id
                            answer.text = text1
                            answer.showAlert = true
                            try {
                                execute(answer)
                            } catch (e: TelegramApiException) {
                                e.printStackTrace()
                            }
                            join(chat_id)
                    }
                    var new_delete = DeleteMessage()
                            .setChatId(chat_id)
                            .setMessageId(message_id)
                    try {
                        execute(new_delete)
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }
                }
                else if(call_data=="back")
                {
                    join(chat_id)
                }
                else if(call_data!="male"&&call_data!="female") {
                    val answer = AnswerCallbackQuery()
                    answer.callbackQueryId = update.callbackQuery.id
                    answer.text = "Принято!!"
                    answer.showAlert = false
                    try {
                        execute(answer)
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }

                    var gender = users.get(chat_id)
                    var position = users_pos.get(chat_id)
                    var lang = user_lang.get(chat_id)
                    var i = 0
                    while (i < 24) {
                        if (call_data == "${i + 1}")
                        {
                            if (gender != null&&lang!=null) {
                                if(position==0)
                                {
                                    user_registor.put(chat_id,0)
                                    var test=user_registor.get(chat_id)
                                    if(test==0)
                                    {
                                    profile.put(chat_id, emojies[i])
                                    abc.write(chat_id, gender, 0, "${emojies[i]}",lang,0,0)
                                    user_registor.replace(chat_id,1)
                                    }

                                    join(chat_id)
                                }
                                if(position==2)
                                {
                                    abc.update_emoji(chat_id,"${emojies[i]}")
                                    profile.replace(chat_id,emojies[i])
                                }


                            }
                            val new_message = DeleteMessage()
                                    .setChatId(chat_id)
                                    .setMessageId(message_id)
                            try {
                                execute(new_message)
                            } catch (e: TelegramApiException) {
                                e.printStackTrace()
                            }

                        }

                        if(call_data=="L$i")
                        {

                            if(position==null)
                            {
                                user_lang.put(chat_id,i)
                                users_pos.put(chat_id,0)
                                gender(chat_id)
                            }
                            if(position==2&&lang!=null)
                            {
                                abc.update_lang(chat_id,lang)
                                user_lang.replace(chat_id,i)
                            }
                            val new_message = DeleteMessage()
                                    .setChatId(chat_id)
                                    .setMessageId(message_id)
                            try {
                                execute(new_message)
                            } catch (e: TelegramApiException) {
                                e.printStackTrace()
                            }
                        }
                        i++
                    }

                }

            }
        }
    }


    fun posting(user_id: Long,f_id: String,message_text: String)
    {
        var i =0
        while(i<database_size)
        {
            var new_message = SendPhoto()
                    .setChatId("${database[i][0]}")
                    .setPhoto(f_id)
                    .setCaption(message_text)
            try {
                execute(new_message)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
            i++
        }

        join(user_id)
    }

    fun get_balance(user_id: Long,message_text: String)
    {
        var text = "Не правильно введены данные.Попробуйте снова"
        var text1 = "Принято!"
        var balance = user_balance.get(user_id)
        if(balance!=null)
        {
            var int = message_text.toInt()
            if (balance < int)
            {
                val new_message = SendMessage()
                        .setChatId(user_id)
                        .setText("$text")
                try {
                    execute(new_message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }

            }
            else{
                user_ostatok.put(user_id,message_text.toInt())
                val new_message = SendMessage()
                        .setChatId(user_id)
                        .setText("$text1")
                try {
                    execute(new_message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
                payment_get_info(user_id)
                payment_data_save_file(user_id,message_text)
                users_pos.replace(user_id,8)
            }
        }
    }

    fun payment_data_save_file(user_id: Long,message_text: String)
    {
        var document_name = "$user_id"
            file.write("$document_name",message_text,user_id)
/*        val initialFile = File("C:\\Users\\HumoFX\\Documents\\TelegramFiles\\$document_name.txt")
        val targetStream = FileInputStream(initialFile)
        if (document_name != null)
        {

            var document = SendDocument()

                    .setChatId(299384140)
                    .setDocument("$document_name.txt",targetStream)

            try {
                execute(document)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }



        }*/
    }

    fun payment_data_send_file(user_id: Long,message_text: String)
    {
        var document_name = "$user_id"
        file.write("$document_name",message_text,user_id)
        val initialFile = File("C:\\Users\\HumoFX\\Documents\\TelegramFiles\\$document_name.txt")
        val targetStream = FileInputStream(initialFile)
        if (document_name != null)
        {

            var document = SendDocument()

                    .setChatId(299384140)
                    .setDocument("$document_name.txt",targetStream)

            try {
                execute(document)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
            join(user_id)



        }
    }

    fun save_qiwi(user_id: Long,message_text: String)
    {
        if(message_text.startsWith("+")) {
            user_qiwi.put(user_id, message_text)
            payment_get_info(user_id)
            payment_data_save_file(user_id,message_text)
            users_pos.replace(user_id,10)
        }
        else {
            var text = "Не правильно введен киви кошелек!"
            val new_message = SendMessage()
                    .setChatId(user_id)
                    .setText("$text")
            try {
                execute(new_message)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }

    }


    fun get_amount(user_id: Long,message_text: String)
    {
        var text = "Введите сумму, которую хотите вывести!"
        val new_message = SendMessage()
                .setChatId(user_id)
                .setText("$text")
        try {
            execute(new_message)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
        users_pos.replace(user_id,7)
    }

    fun gender(user_id: Long)
    {
        var text = "*Добро пожаловать в анонимный чат NONAME!*"+ "\n _Выберите ваш пол:_ "
        var button_text1 = "Мужской \uD83D\uDC81\u200D♂️"
        var button_text2 = "Женский \uD83D\uDC81\u200D♀️"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="*Аноним чатимизга хуш келибсиз!*"+ "\n_Жинсингизни танланг:_"
                button_text1="Эркак \uD83D\uDC81\u200D♂️"
                button_text2="Айол \uD83D\uDC81\u200D♀️"
            }
            if(lang==2)
            {
                text = "*Welcome to our anonymous chat* "+ "\n_Choose ur gender:_"
                button_text1 = "Male \uD83D\uDC81\u200D♂️"
                button_text2 = "Female \uD83D\uDC81\u200D♀️"
            }
        }



        val message = SendMessage() // Create a message object object
                .setChatId(user_id)
                .setParseMode("Markdown")
                .setText("$text")
        val markupInline = InlineKeyboardMarkup()
        val rowsInline = ArrayList<List<InlineKeyboardButton>>()
        val rowInline = ArrayList<InlineKeyboardButton>()
        rowInline.add(InlineKeyboardButton().setText("$button_text1").setCallbackData("male"))
        rowInline.add(InlineKeyboardButton().setText("$button_text2").setCallbackData("female"))
        // Set the keyboard to the markup
        rowsInline.add(rowInline)
        // Add it to the message
        markupInline.keyboard = rowsInline
        message.replyMarkup = markupInline
        try {
            execute(message) // Sending our message object to user
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    fun language(user_id: Long)
    {
        val message = SendMessage() // Create a message object object
                .setChatId(user_id)
                .setParseMode("Markdown")
                .setText("*Выберете язак на котором хотите общаться!\uD83C\uDDF7\uD83C\uDDFA*"+"\n*Cухбатлашмокчи болган тилни талланг!\uD83C\uDDFA\uD83C\uDDFF*"+"\n*Choose the language to chat with!\uD83C\uDDEC\uD83C\uDDE7*")
        var markupInline = InlineKeyboardMarkup()
        val rowsInline = ArrayList<List<InlineKeyboardButton>>()
        val rowInline = ArrayList<InlineKeyboardButton>()
        var i = 0
        while(i<3)
        {
            rowInline.add(InlineKeyboardButton().setText("${language[i]}").setCallbackData("L$i"))
            i++
        }
        rowsInline.add(rowInline)
        markupInline.keyboard = rowsInline
        message.replyMarkup = markupInline
        try {
            execute(message) // Sending our message object to user
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    fun profile_emojiM(user_id: Long)
    {
        var text = "Выберете 1 смайлик для вашего профиля!"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Proflingizga 1 smaylik tallang!"
            }
            if(lang==2)
            {
                text = "Choose 1 emoji for your profile"
            }
        }



            val message = SendMessage() // Create a message object object
                    .setChatId(user_id)
                    .setParseMode("Markdown")
                    .setText("*$text*")

        var markupInline = InlineKeyboardMarkup()
        val rowsInline = ArrayList<List<InlineKeyboardButton>>()
        val rowInline1 = ArrayList<InlineKeyboardButton>()
        val rowInline2 = ArrayList<InlineKeyboardButton>()
        val rowInline3 = ArrayList<InlineKeyboardButton>()
        var i =0
        while (i<4)
        {
            rowInline1.add(InlineKeyboardButton().setText("${emojies[i]}").setCallbackData("${i+1}"))
            i++
        }
        while (i<8)
        {
            rowInline2.add(InlineKeyboardButton().setText("${emojies[i]}").setCallbackData("${i+1}"))
            i++
        }
        while (i<12)
        {
            rowInline3.add(InlineKeyboardButton().setText("${emojies[i]}").setCallbackData("${i+1}"))
            i++
        }

        // Set the keyboard to the markup
        rowsInline.add(rowInline1)
        rowsInline.add(rowInline2)
        rowsInline.add(rowInline3)
        // Add it to the message
        markupInline.keyboard = rowsInline
        message.replyMarkup = markupInline
        try {
            execute(message) // Sending our message object to user
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }

    }

    fun profile_emojiJ(user_id: Long)
    {
        var text = "Выберете 1 смайлик для вашего профиля!"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Proflingizga 1 smaylik tallang!"
            }
            if(lang==2)
            {
                text = "Choose 1 emoji for your profile"
            }
        }

        val message = SendMessage() // Create a message object object
                .setChatId(user_id)
                .setParseMode("Markdown")
                .setText("*$text*")
        var markupInline = InlineKeyboardMarkup()
        val rowsInline = ArrayList<List<InlineKeyboardButton>>()
        val rowInline1 = ArrayList<InlineKeyboardButton>()
        val rowInline2 = ArrayList<InlineKeyboardButton>()
        val rowInline3 = ArrayList<InlineKeyboardButton>()
        var i =12
        while (i<16)
        {
            rowInline1.add(InlineKeyboardButton().setText("${emojies[i]}").setCallbackData("${i+1}"))
            i++
        }
        while (i<20)
        {
            rowInline2.add(InlineKeyboardButton().setText("${emojies[i]}").setCallbackData("${i+1}"))
            i++
        }
        while (i<24)
        {
            rowInline3.add(InlineKeyboardButton().setText("${emojies[i]}").setCallbackData("${i+1}"))
            i++
        }

        // Set the keyboard to the markup
        rowsInline.add(rowInline1)
        rowsInline.add(rowInline2)
        rowsInline.add(rowInline3)
        // Add it to the message
        markupInline.keyboard = rowsInline
        message.replyMarkup = markupInline
        try {
            execute(message) // Sending our message object to user
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }

    }

    fun profile(user_id: Long)
    {
        var text = "Настройте свой профиль"
        var lang = user_lang.get(user_id)
        var emoji = profile.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Profilingizni sozlang"
            }
            if(lang==2)
            {
                text = "Setup ur profile"
            }
        }

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        val message1="_${text}_"
        val sendMessage = SendMessage()
                .setChatId(user_id)
                .setParseMode("Markdown")
                .setText(message1)

        sendMessage.setReplyMarkup(replyKeyboardMarkup)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false
        val keyboard = ArrayList<KeyboardRow>()
        val keyboardFirstRow = KeyboardRow()
        val keyboardSecondRow = KeyboardRow()
        val keyboardThirdRow = KeyboardRow()
        keyboardFirstRow.add(KeyboardButton("\uD83C\uDDF7\uD83C\uDDFA\uD83D\uDD04\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04\uD83C\uDDEC\uD83C\uDDE7 "))
        keyboardFirstRow.add(KeyboardButton("$emoji"))
        keyboardSecondRow.add(KeyboardButton("Баланс"))
        keyboardSecondRow.add(KeyboardButton("Реф. линк"))
        keyboardThirdRow.add(KeyboardButton("\uD83D\uDD19"))
        keyboard.add(keyboardFirstRow)
        keyboard.add(keyboardSecondRow)
        keyboard.add(keyboardThirdRow)
        replyKeyboardMarkup.keyboard = keyboard
        try {
            execute(sendMessage)
      //      execute(delmessage)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
        abc.update(user_id,2)
     //   users_pos.put(user_id,2)
        users_pos.replace(user_id,2)
        println("pos: ${users_pos.get(user_id)}")

    }

    fun ban(user_id: Long,message_text: String)
    {
        var text = "ВЫ ЗАБАНЕНЫ ЗА НЕ СОБЛЮДЕНИЕ ПРАВИЛ ЧАТА"
        var lang = user_lang.get(message_text.toLong())
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Sizning profilingiz Chat ko'rsatmalariga rioya qilmaslik sababi bo'yicha BANlandi "
            }
            if(lang==2)
            {
                text = "YOU ARE BANNED FOR THE NON-COMPLIANCE OF CHAT RULES"
            }
        }
        val message = SendMessage()
                .setChatId(message_text)
                .setParseMode("Markdown")
                .setText("*${text}*")
        try {
            execute(message)
            //      execute(delmessage)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
        user_banned.put(message_text.toLong(),1)
        abc.update_ban(message_text.toLong(),1)
        join(user_id)

    }

    fun unban(user_id: Long,message_text: String)
    {
        var text = "ВЫ РАЗБАНЕНЫ"
        var lang = user_lang.get(message_text.toLong())
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="BANDAN OZOD BO'LDINGIZ "
            }
            if(lang==2)
            {
                text = "YOU ARE UNBANNED"
            }
        }
        val message = SendMessage()
                .setChatId(message_text)
                .setParseMode("Markdown")
                .setText("*$text*")
        try {
            execute(message)
            //      execute(delmessage)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
        if(user_banned.containsKey(message_text.toLong()))
        {
            user_banned.put(message_text.toLong(), 0)
            abc.update_ban(message_text.toLong(), 0)

        }
        else{
            val message = SendMessage()
                    .setChatId(user_id)
                    .setParseMode("Markdown")
                    .setText("*Нет в списке забаненных*")
            try {
                execute(message)
                //      execute(delmessage)
            }
            catch (e: TelegramApiException){
                e.printStackTrace()
            }
        }
        join(user_id)


    }
    fun warning(user_id: Long,message_text: String)
    {
        var text = "*АДМИНИСТРАЦИЯ БОТА ПРЕДУПРЕЖДАЕТ ВАС О ТОМ, ЧТО ВЫ НАРУШИЛИ ПРАВИЛА БОТА!!!ПРИ ПОСЛЕДУЮЩИХ НАРУШЕНИЙ ВАС МОГУТ ЗАБАНИТЬ НА ОПРЕДЕЛЕННЫЙ СРОК!*"
        var lang = user_lang.get(message_text.toLong())
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="BOT ADMINISTRATSIYASI SIZNI BOT QOIDALARINI BUZGANLIGINGIZ XAQIDA OGOHLANTIRADI!!!QOIDALARGA RIOYA QILMASLIK NATIJASIDA BAN HOLATIGA TUSHUSHINGIZ MUMKIN! "
            }
            if(lang==2)
            {
                text = "BOT's ADMINISTRATION WARNS YOU ABOUT THAT YOU HAVE VIOLATED THE BOT's RULES !!! THROUGH SUBSEQUENT VIOLATIONS YOU MAY BE BANNED FOR A DEFINED TIME!"
            }
        }
        var message =SendMessage()
                .setChatId("${message_text.toLong()}")
                .setParseMode("Markdown")
                .setText("$text")
        try {
            execute(message)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
        join(user_id)

    }
    fun join(user_id: Long){
        var text = "Начните общение"
        var button_text1 = "Найти собеседника \uD83D\uDD0E"
        var button_text2 = "Статистика \uD83D\uDCCA"
        var button_text3 = "Правила \uD83D\uDCDA"
        var button_text4 = "Пожелания \uD83D\uDCDD"
        var button_text5 = "BAN"
        var button_text6 = "UNBAN"
        var button_text7 = "WARNING"
        var button_text8 = "POSTING"
        var lang = user_lang.get(user_id)
        println("lang $lang")
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Сухбатлашишни хохлайсизми?"
                button_text1="Сухбатдош излаш \uD83D\uDD0E"
                button_text3="Коидалар \uD83D\uDCDA"
                button_text4="Тилаклар \uD83D\uDCDD"

            }
            if(lang==2)
            {
                text = "Would u like to speak?"
                button_text1="Look for a companion \uD83D\uDD0E"
                button_text2="Statistics \uD83D\uDCCA"
                button_text3="Rules \uD83D\uDCDA"
                button_text4="Your wishes \uD83D\uDCDD"
            }
        }


        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        val sendMessage = SendMessage()
                .setChatId(user_id)
               // .enableMarkdown(true)
                .setParseMode("Markdown")
                .setText("_${text}_")

        sendMessage.setReplyMarkup(replyKeyboardMarkup)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false
        val keyboard = ArrayList<KeyboardRow>()
        val keyboardFirstRow = KeyboardRow()
        val keyboardSecondRow = KeyboardRow()
        val keyboardThirdRow = KeyboardRow()
        val keyboardFourthRow = KeyboardRow()
        val keyboardFivthRow = KeyboardRow()
        val profilename = profile.get(user_id)
        keyboardFirstRow.add(KeyboardButton("$button_text1"))
        keyboardSecondRow.add(KeyboardButton("$profilename"))
        keyboardSecondRow.add(KeyboardButton("$button_text2"))
        keyboardSecondRow.add(KeyboardButton("$button_text3"))
        keyboardThirdRow.add(KeyboardButton("$button_text4"))
        keyboardFourthRow.add(KeyboardButton("$button_text5"))
        keyboardFourthRow.add(KeyboardButton("$button_text6"))
        keyboardFourthRow.add(KeyboardButton("$button_text7"))
        keyboardFivthRow.add(KeyboardButton("$button_text8"))
        keyboard.add(keyboardFirstRow)
        keyboard.add(keyboardSecondRow)
        keyboard.add(keyboardThirdRow)
        if (user_id.toInt()==299384140)
        {
            keyboard.add(keyboardFourthRow)
            keyboard.add(keyboardFivthRow)
        }

        replyKeyboardMarkup.keyboard = keyboard
        try {
            execute(sendMessage)

        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }

        abc.update(user_id,0)
        users_pos.replace(user_id,0)
       // users_pos.put(user_id,0)
        //users_pos[user_id]=0
       // println("pos: ${users_pos.get(user_id)}")
    }
    fun payment_get_info (user_id: Long)
    {
        var button_text1 = "Продолжить"
   //     var button_text2 = "Изменить"
        var button_text3 = "Отмена"
        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        val sendMessage = SendMessage()
                .setChatId(user_id)
                .setText("Продолжить?")
        sendMessage.setReplyMarkup(replyKeyboardMarkup)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false
        val keyboard = ArrayList<KeyboardRow>()
        val keyboardFirstRow = KeyboardRow()
        val keyboardSecondRow = KeyboardRow()
        keyboardFirstRow.add(KeyboardButton("$button_text1"))
  //      keyboardFirstRow.add(KeyboardButton("$button_text2"))
        keyboardSecondRow.add(KeyboardButton("$button_text3"))
        keyboard.add(keyboardFirstRow)
        keyboard.add(keyboardSecondRow)

        replyKeyboardMarkup.keyboard = keyboard
        try
        {
            execute(sendMessage)
        }
        catch (e: TelegramApiException)
        {
            e.printStackTrace()
        }
    }

    fun end_conversation(user_id: Long){
        var text = "Вы соеденены"
        var button_text1 = "Остановить общение \uD83D\uDCDB"
        var button_text2 = "СПАМ"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Сухбатдош топилди"
                button_text1="Сухбатни тохтатиш \uD83D\uDCDB"
            }
            if(lang==2)
            {
                text = "Companion was found"
                button_text1="Stop chating \uD83D\uDCDB"
                button_text2="SPAM"
            }
        }


        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        val sendMessage = SendMessage()
                .setChatId(user_id)
                // .enableMarkdown(true)
                .setParseMode("Markdown")
                .setText("_${text}_")

        sendMessage.setReplyMarkup(replyKeyboardMarkup)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false
        val keyboard = ArrayList<KeyboardRow>()
        val keyboardFirstRow = KeyboardRow()
        keyboardFirstRow.add(KeyboardButton("$button_text1"))
        keyboardFirstRow.add(KeyboardButton("$button_text2"))
        keyboard.add(keyboardFirstRow)
        replyKeyboardMarkup.keyboard = keyboard
        try {
            execute(sendMessage)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
    }


    fun end_search(user_id: Long){
        var text = "Ваш собеседник находится в поиске"
        var button_text1 = "Остановить поиск ❌"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Сухбатдош кидирилмокда"
                button_text1="Кидирувни тохтатиш ❌"
            }
            if(lang==2)
            {
                text = "Companion is in search"
                button_text1="Stop searching ❌"
            }
        }


        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        val sendMessage = SendMessage()
                .setChatId(user_id)
                // .enableMarkdown(true)
                .setParseMode("Markdown")
                .setText("_${text}_")


        sendMessage.setReplyMarkup(replyKeyboardMarkup)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false
        val keyboard = ArrayList<KeyboardRow>()
        val keyboardFirstRow = KeyboardRow()
        keyboardFirstRow.add(KeyboardButton("$button_text1"))
        keyboard.add(keyboardFirstRow)
        replyKeyboardMarkup.keyboard = keyboard
        try {
            execute(sendMessage)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
        abc.update(user_id,1)
        users_pos.replace(user_id,1)
        //users_pos.put(user_id,1)
       // users_pos[user_id]=1
        println("pos: ${users_pos.get(user_id)}")

    }

    fun referal(user_id: Long)
    {
        var iter = user_referals.get(user_id)
        var text = "Вы пригласили $iter рефералов\n Скопируйте эту ссылку. и отправьте своим друьям. За каждого приглашенного вы получите 2.5-endorphins.1-endorphin - это криптовалюта в нашем боте.Вы можете её копить и обменять на реальные деньги. Курс валют:\n1-endorphin = 75 sum \n1-endorphin = 0,5 rub \n Минимальный вывод с 50-endorphins "
        var message = SendMessage()
                .setChatId(user_id)
                .setText("$text"+"\n t.me/Hum0bot?start=${user_id}")
        try {
            execute(message)
        }
        catch (e: TelegramApiException){
            e.printStackTrace()
        }
    }
    fun parse_link(user_id: Long,message_text: String)
    {
        if(message_text.startsWith("/start ")) {
            var get_user_id = message_text
            var time = get_user_id.substring(7)
            println("GETUSERID= ${time}")
            var iter = user_referals.get(time.toLong())
            if (iter != null) {
                user_referals.replace(time.toLong(), iter.plus(1))
                abc.update_referal(time.toLong(), iter + 1)
            }

            var text = "Вы получили 100 rub за приглашение участника"
            var balance = user_balance.get(time.toLong())
            if(balance!=null)
            {
                var integer = balance.plus(100)

                    user_balance.replace(time.toLong(),integer)

            }
            else user_balance.put(time.toLong(),100)


            var message = SendMessage()
                    .setChatId(time)
                    .setText(text)
            try {
                execute(message)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
    }
    fun balans (user_id: Long,message_text: String)
    {

        var iter=user_referals.get(user_id)
        var coin=0.0
        var balance = user_balance.get(user_id)
        println("$ = $balance")
        if(iter!=null && balance!=null)
        {
            coin =balance.toDouble()
            println("$ = $balance")
        }


        var text="Ваш баланс составляет $coin rub\n Для вывода необходимо 30 rub"
        var button_text1 = "Вывод"
        var button_text2 = "Отмена"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                text="Сизнинг балансингизда $coin rub \n Пулнингизни чикариб олиш учун балансингизда 30 rub бо'лиши зарур"

            }
            if(lang==2)
            {
                text = "You have $coin rub \n Minimum output is 30 rub"
            }
            val message = SendMessage()
                    .setChatId(user_id)
                    .setParseMode("Markdown")
                    .setText("*${text}*")
            val markupInline = InlineKeyboardMarkup()
            val rowsInline = ArrayList<List<InlineKeyboardButton>>()
            val rowInline = ArrayList<InlineKeyboardButton>()
            rowInline.add(InlineKeyboardButton().setText("$button_text1").setCallbackData("rub"))
            rowInline.add(InlineKeyboardButton().setText("$button_text2").setCallbackData("back"))
            // Set the keyboard to the markup
            rowsInline.add(rowInline)
            // Add it to the message
            markupInline.keyboard = rowsInline
            message.replyMarkup = markupInline
            try {
                execute(message)
                //      execute(delmessage)
            }
            catch (e: TelegramApiException){
                e.printStackTrace()
            }
        }
    }
    private fun button_group_0(message_text: String, user_id: Long)
    {
        var button_text1 = "Найти собеседника \uD83D\uDD0E"
        var button_text2 = "Статистика \uD83D\uDCCA"
        var button_text3 = "Правила \uD83D\uDCDA"
        var button_text4 = "Пожелания \uD83D\uDCDD"
        var lang = user_lang.get(user_id)
        println("lang $lang")
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                button_text1="Сухбатдош излаш \uD83D\uDD0E"
                button_text3="Коидалар \uD83D\uDCDA"
                button_text4="Тилаклар \uD83D\uDCDD"

            }
            if(lang==2)
            {

                button_text1="Look for a companion \uD83D\uDD0E"
                button_text2="Statistics \uD83D\uDCCA"
                button_text3="Rules \uD83D\uDCDA"
                button_text4="Your wishes \uD83D\uDCDD"
            }
        }



        val profilename = profile.get(user_id)
            when(message_text)
            {
                "$profilename"->
                {
                    profile(user_id)

                }


                "$button_text2"->
                {


                    var percentM = (male_size.toDouble()/all_size.toDouble())*100
                    var percentJ = ((female_size.toDouble()/all_size.toDouble())*100)
                    val message = SendMessage()
                            .setChatId(user_id)
                            .setParseMode("Markdown")
                            .setText("*Статистика *"+"\n Нас уже: $all_size "+"\n Мужчин: ${percentM.toInt()}% " + "\n Женщин: ${percentJ.toInt()}%")
                    try {
                        execute(message)
                    }
                    catch (e:TelegramApiException)
                    {
                        e.printStackTrace()
                    }
                }

                "$button_text1" -> {

                    var tmp = users.get(user_id)
                    if(tmp=="male")
                    {
                        var bool1 = partner_searchM.contains(user_id.toInt())
                        var bool2 = partner_searchMU.contains(user_id.toInt())
                        var bool3 = partner_searchME.contains(user_id.toInt())
                        var bool_con = connected_userM.contains(user_id)
                        println("bool $bool1  bool_con $bool_con")
                        if(bool1==false&&bool2==false&&bool3==false&&bool_con==false){
                            if(lang==0)
                            {
                                partner_searchM.add(user_id.toInt())
                            }
                            if(lang==1)
                            {
                                partner_searchMU.add(user_id.toInt())
                            }
                            if(lang==2)
                            {
                                partner_searchME.add(user_id.toInt())
                            }

                            abc.write_SEARCH(user_id,"male")
                            end_search(user_id)


                            println("UV : ${users.values}  UK : ${users.keys} tmp = $tmp")}
                        else{
                            var message_text1 = "```Неправильная команда```"

                            val message = SendMessage()
                                    .setChatId(user_id)
                                    .setParseMode("Markdown")
                                    .setText(message_text1)
                            try {
                                execute(message)
                            }
                            catch (e:TelegramApiException)
                            {
                                e.printStackTrace()
                            }
                        }

                    }
                    else
                    {
                        var bool1 = partner_searchJ.contains(user_id.toInt())
                        var bool2 = partner_searchJU.contains(user_id.toInt())
                        var bool3 = partner_searchJE.contains(user_id.toInt())
                        var bool_con = connected_userJ.contains(user_id)
                        if(bool1==false&&bool2==false&&bool3==false&&bool_con==false)
                        {
                            if(lang==0)
                            {
                                partner_searchJ.add(user_id.toInt())
                            }
                            if(lang==1)
                            {
                                partner_searchJU.add(user_id.toInt())
                            }
                            if(lang==2)
                            {
                                partner_searchJE.add(user_id.toInt())
                            }
                            abc.write_SEARCH(user_id,"female")
                            end_search(user_id)
                           /* abc.update(user_id,1)
                            users_pos.replace(user_id,1)*/


                            println("UV : ${users.values}  UK : ${users.keys}")
                        }
                        else{
                            var message_text1 = "```Неправильная команда```"

                            val message = SendMessage()
                                    .setChatId(user_id)
                                    .setParseMode("Markdown")
                                    .setText(message_text1)
                            try {
                                execute(message)
                            }
                            catch (e:TelegramApiException)
                            {
                                e.printStackTrace()
                            }
                        }
                    }

                }
                "$button_text3"->
                {
                    //Здесь будут правила
                    var text = qiwi.payment()
                    var message = SendMessage()
                            .setChatId(user_id)
                            .setText("$text")
                    try {
                        execute(message)
                    }
                    catch (e:TelegramApiException)
                    {
                        e.printStackTrace()
                    }
                }
                "$button_text4"->
                {
                    //Связь с админами
                    var message = SendMessage()
                            .setChatId(user_id)
                            .setParseMode("Markdown")
                            .setText("*Пройдите по*"+ " [ссылке](t.me/ChatRuletka2bot?start) "+ "*для связи с нами* ")
                    try {
                        execute(message)
                    }
                    catch (e:TelegramApiException)
                    {
                        e.printStackTrace()
                    }
                }
            }

    }
    private fun button_group_1 (message_text: String, user_id: Long)
    {
        var button_text1 = "Остановить поиск ❌"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                button_text1="Кидирувни тохтатиш ❌"
            }
            if(lang==2)
            {
                button_text1="Stop searching ❌"
            }
        }
        when (message_text) {


            "$button_text1" -> {

                var tmp = users.get(user_id)
                if(tmp=="male")
                {
                    var bool1 = partner_searchM.contains(user_id.toInt())
                    var bool2 = partner_searchMU.contains(user_id.toInt())
                    var bool3 = partner_searchME.contains(user_id.toInt())
                    if(bool1==true)
                    {
                        partner_searchM.remove(user_id.toInt())
                        abc.delete_SEARCH(user_id)
                        join(user_id)

                    }
                    if(bool2==true)
                    {
                        partner_searchMU.remove(user_id.toInt())
                        abc.delete_SEARCH(user_id)
                        join(user_id)

                    }
                    if(bool3==true)
                    {
                        partner_searchME.remove(user_id.toInt())
                        abc.delete_SEARCH(user_id)
                        join(user_id)

                    }

                }
                else
                {
                    var bool1 = partner_searchJ.contains(user_id.toInt())
                    var bool2 = partner_searchJU.contains(user_id.toInt())
                    var bool3 = partner_searchJE.contains(user_id.toInt())
                    if(bool1==true)
                    {
                        partner_searchJ.remove(user_id.toInt())
                        abc.delete_SEARCH(user_id)
                        join(user_id)

                    }
                    if(bool2==true)
                    {
                        partner_searchJU.remove(user_id.toInt())
                        abc.delete_SEARCH(user_id)
                        join(user_id)

                    }
                    if(bool3==true)
                    {
                        partner_searchJE.remove(user_id.toInt())
                        abc.delete_SEARCH(user_id)
                        join(user_id)

                    }
                }


            }
        }
    }
    private fun button_group_2(message_text: String, user_id: Long)
    {
        var emoji = profile.get(user_id)
        var gender = users.get(user_id)
            when(message_text)
            {
                "\uD83C\uDDF7\uD83C\uDDFA\uD83D\uDD04\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04\uD83C\uDDEC\uD83C\uDDE7"->
                {
                    language(user_id)
                }
                "\uD83D\uDD19" ->
                {
                    join(user_id)
                }
                "$emoji"->
                {

                    if(gender=="male")
                    {
                        profile_emojiM(user_id)
                    }
                    else{
                        profile_emojiJ(user_id)
                    }
                }
                "Баланс"->
                {
                    balans(user_id,message_text)
                    var test = users_map.get(user_id)
                    if (test!=null){
                    println("TEST ${test[1]}")}
                }
                "Реф. линк"->
                {
                    referal(user_id)

                }
            }

    }
    private fun button_group_3(message_text: String, user_id: Long,user_id1: Long)
    {
        var text1="_Вы остановили общение_"
        var text2="_Ваш собеседник остановил общение_"
        var text3="_Вы отправили жалобу_"
        var text4="_Ваш собеседник отправил на вас жалобу_"
        var button_text1 = "Остановить общение \uD83D\uDCDB"
        var button_text2 = "СПАМ"
        var lang = user_lang.get(user_id)
        if(lang!=null)
        {
            if(lang==0)
            {

            }
            if(lang==1)
            {
                button_text1="Сухбатни тохтатиш \uD83D\uDCDB"
                text1="_Сиз сухбатни тохтатдингиз_"
                text2="_Сухбат тохтатилди_"
                text3="_Сиз сухбатдошингиз устидан арз килдингиз_"
                text4="_Сухбатдошингиз сизнинг устингиздан арз килди_"
            }
            if(lang==2)
            {
                button_text1="Stop chating \uD83D\uDCDB"
                button_text2="SPAM"
                text1="_You stopped chating_"
                text2="_Your companion stopped chating_"
                text3="_You submitted a complaint_"
                text4="_Your partner lodged a complaint against you_"
            }
        }

        when(message_text)
        {
            "$button_text1"->
            {
                val message = SendMessage()
                        .setChatId(user_id)
                        .setParseMode("Markdown")
                        .setText("$text1")
                try {
                    execute(message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
                val message1 = SendMessage()
                        .setChatId(user_id1)
                        .setParseMode("Markdown")
                        .setText("$text2")
                try {
                    execute(message1)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }

                join(user_id1)
                join(user_id)
                file_nameJ.remove(user_id)
                file_nameM.remove(user_id1)
                abc.delete_connected(user_id1)
                abc.delete_connected(user_id)

            }

            "$button_text2"->
            {
                val message = SendMessage()
                        .setChatId(user_id)
                        .setParseMode("Markdown")
                        .setText("$text3")
                try {
                    execute(message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
                val message1 = SendMessage()
                        .setChatId(user_id1)
                        .setParseMode("Markdown")
                        .setText("$text4")
                try {
                    execute(message1)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
                var document_name = file_nameJ.get(user_id)


                if(document_name==null)
                {
                    document_name=file_nameM.get(user_id)
                }
                file.write("${document_name.toString()}","- нажал на СПАМ",user_id)
                val initialFile = File("C:\\Users\\HumoFX\\Documents\\TelegramFiles\\$document_name.txt")
                val targetStream = FileInputStream(initialFile)
                if (document_name != null)
                {
                    if (document_name%2==0)
                    {
                        var document = SendDocument()

                                .setChatId(299384140)
                                .setDocument("$document_name.txt",targetStream)

                        try {
                            execute(document)
                        } catch (e: TelegramApiException) {
                            e.printStackTrace()
                        }

                    }
                    else
                    {

                        var document = SendDocument()

                                .setChatId(162601105)
                                .setDocument("$document_name.txt",targetStream)

                        try {
                            execute(document)
                        } catch (e: TelegramApiException) {
                            e.printStackTrace()
                        }
                    }

                }
                join(user_id1)
                join(user_id)
                file_nameJ.remove(user_id)
                file_nameM.remove(user_id1)
                abc.delete_connected(user_id1)
                abc.delete_connected(user_id)
            }
        }
    }
    private fun button_group_4(message_text: String, user_id: Long)
    {
        when(message_text)
        {
            "BAN"->
            {
                users_pos.replace(user_id,3)
                abc.update(user_id,3)
                val message =SendMessage()
                        .setChatId(user_id)
                        .setText("Введите user_id")
                try {
                    execute(message) // Sending our message object to user
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }

            }
            "UNBAN"->
            {
                users_pos.replace(user_id,4)
                abc.update(user_id,4)
                val message =SendMessage()
                        .setChatId(user_id)
                        .setText("Введите user_id")
                try {
                    execute(message) // Sending our message object to user
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }


            }
            "WARNING"->
            {
                users_pos.replace(user_id,5)
                abc.update(user_id,5)
                val message = SendMessage()
                        .setChatId(user_id)
                        .setText("Введите user_id для отправки предупреждения")
                try {
                    execute(message) // Sending our message object to user
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }

            }
            "POSTING"->
            {
                users_pos.replace(user_id,9)
                abc.update(user_id,9)
                val message = SendMessage()
                        .setChatId(user_id)
                        .setText("Do a new post")
                try {
                    execute(message) // Sending our message object to user
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            }
        }
    }
    private fun button_group_5(message_text: String,user_id: Long)
    {
        var button1 = "Продолжить"
        var button2 = "Изменить"
        var button3 = "Отмена"
        when(message_text)
        {
            button1->
            {
                var pos = users_pos.get(user_id)
                if(pos==10)
                {
                    get_amount(user_id,message_text)

                }
                if(pos==8)
                {
                    var ost = user_ostatok.get(user_id)
                    var balance = user_balance.get(user_id)
                    var ostatok = balance!! - ost!!
                    user_balance.replace(user_id,ostatok)
                    var text = "Ваш запрос в обработке"
                    var message =  SendMessage()
                            .setChatId(user_id)
                            .setText("$text")
                    try
                    {
                        execute(message)
                    }
                    catch (e: TelegramApiException)
                    {
                        e.printStackTrace()
                    }
                    payment_data_send_file(user_id,message_text)

                }
            }
            button2->
            {

            }
            button3->
            {
                join(user_id)
            }
        }
    }

    fun connection(user_id: Long, message_text: String) {


        println("Array : ${partner_searchJ.size}  ${partner_searchM.size}")
        if (partner_searchJ.size != 0 && partner_searchM.size !=0) {
            if(message_text.equals("Найти собеседника \uD83D\uDD0E")){
            }
            connected_userJ.add(partner_searchJ[0].toLong())
            connected_userM.add(partner_searchM[0].toLong())
            abc.write_conn(partner_searchM[0].toLong(),"male")
            abc.write_conn(partner_searchJ[0].toLong(),"female")
            var p=connected_userJ.size
            var k=connected_userM.size
            end_conversation(connected_userJ[p - 1])
            end_conversation(connected_userM[k - 1])
            file_iterator++
            file_nameJ.put(partner_searchJ[0].toLong(),file_iterator)
            file_nameM.put(partner_searchM[0].toLong(),file_iterator)
            abc.delete_SEARCH(partner_searchM[0].toLong())
            abc.delete_SEARCH(partner_searchJ[0].toLong())
            partner_searchJ.removeAt(0)
            partner_searchM.removeAt(0)

        }
        if (partner_searchJU.size != 0 && partner_searchMU.size !=0) {
            if(message_text.equals("Сухбатдош излаш \uD83D\uDD0E")){
            }
            connected_userJ.add(partner_searchJU[0].toLong())
            connected_userM.add(partner_searchMU[0].toLong())
            abc.write_conn(partner_searchMU[0].toLong(),"male")
            abc.write_conn(partner_searchJU[0].toLong(),"female")
            var p=connected_userJ.size
            var k=connected_userM.size
            end_conversation(connected_userJ[p - 1])
            end_conversation(connected_userM[k - 1])
            file_iterator++
            file_nameJ.put(partner_searchJ[0].toLong(),file_iterator)
            file_nameM.put(partner_searchM[0].toLong(),file_iterator)
            abc.delete_SEARCH(partner_searchMU[0].toLong())
            abc.delete_SEARCH(partner_searchJU[0].toLong())
            partner_searchJU.removeAt(0)
            partner_searchMU.removeAt(0)

        }
        if (partner_searchJE.size != 0 && partner_searchME.size !=0) {
            if(message_text.equals("Look for a companion \uD83D\uDD0E")){
            }
            connected_userJ.add(partner_searchJE[0].toLong())
            connected_userM.add(partner_searchME[0].toLong())
            abc.write_conn(partner_searchME[0].toLong(),"male")
            abc.write_conn(partner_searchJE[0].toLong(),"female")
            var p=connected_userJ.size
            var k=connected_userM.size
            end_conversation(connected_userJ[p - 1])
            end_conversation(connected_userM[k - 1])
            file_iterator++
            file_nameJ.put(partner_searchJE[0].toLong(),file_iterator)
            file_nameM.put(partner_searchME[0].toLong(),file_iterator)
            abc.delete_SEARCH(partner_searchME[0].toLong())
            abc.delete_SEARCH(partner_searchJE[0].toLong())
            partner_searchJE.removeAt(0)
            partner_searchME.removeAt(0)

        }

    }

    fun connected(user_id: Long,message_text: String,user_firstname: String) {
        var test = 0
        var tmp = users.get(user_id)
        if(tmp=="male") {
            var bool = connected_userM.contains(user_id)
            if(bool==true)
            {
                test=1
            }
        }
        else{
            var bool = connected_userJ.contains(user_id)
            if(bool==true)
            {
                test=1
            }
        }
        if(test==1) {
            var p = connected_userM.size
            var k = connected_userJ.size
            if (message_text.equals("Найти собеседника \uD83D\uDD0E")||message_text.equals("Сухбатдош излаш \uD83D\uDD0E")||message_text.equals("Look for a companion \uD83D\uDD0E")) {

            } else {

                while (k > 0) {

                    while (p > 0) {

                        k--
                        p--

                            if (user_id == connected_userJ[p]) {
                                if (message_text.equals("Остановить общение \uD83D\uDCDB")||message_text.equals("Сухбатни тохтатиш \uD83D\uDCDB")||message_text.equals("Stop chating \uD83D\uDCDB")) {
                                    button_group_3(message_text,connected_userJ[p],connected_userM[k])
                                    connected_userJ.removeAt(p)
                                    connected_userM.removeAt(k)
                                }
                                if (message_text.equals("СПАМ")||message_text.equals("SPAM")) {
                                    button_group_3(message_text,connected_userJ[p],connected_userM[k])
                                    connected_userJ.removeAt(p)
                                    connected_userM.removeAt(k)
                                }
                                else {
                                    var file_name = file_nameJ.get(user_id)
                                    file.write("${file_name.toString()}", message_text, user_id)
                                    var profilename = profile.get(user_id)
                                    val message = SendMessage()
                                            .setChatId(connected_userM[k])
                                            .setParseMode("Markdown")
                                            .setText("$profilename : $message_text")
                                    try {
                                        execute(message)
                                    } catch (e: TelegramApiException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                            if (user_id == connected_userM[k]) {
                                if(message_text.equals("Остановить общение \uD83D\uDCDB")||message_text.equals("Сухбатни тохтатиш \uD83D\uDCDB")||message_text.equals("Stop chating \uD83D\uDCDB")) {
                                    button_group_3(message_text,connected_userM[k],connected_userJ[p])
                                    connected_userJ.removeAt(p)
                                    connected_userM.removeAt(k)
                                }
                                if (message_text.equals("СПАМ")||message_text.equals("SPAM")) {
                                    button_group_3(message_text,connected_userM[k],connected_userJ[p])
                                    connected_userJ.removeAt(p)
                                    connected_userM.removeAt(k)
                                }
                                else {
                                    var file_name = file_nameM.get(user_id)
                                    file.write("${file_name.toString()}", message_text, user_id)
                                    var profilename = profile.get(user_id)
                                    val message = SendMessage()
                                            .setChatId(connected_userJ[p])
                                            .setParseMode("Markdown")
                                            .setText("$profilename : $message_text")
                                    try {
                                        execute(message)
                                    } catch (e: TelegramApiException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

    private fun log(
            first_name: String,
            user_username: String,
            user_id: String,
            txt: String,
            answer: String

    ) {
        println("\n ----------------------------")
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        println(dateFormat.format(date))
        println("Message from $first_name User_name @$user_username. (id = $user_id) \n Text - $txt userspos = ${users_pos.values}")
      //  println("Bot answer: \n Text - $answer")
    }


}