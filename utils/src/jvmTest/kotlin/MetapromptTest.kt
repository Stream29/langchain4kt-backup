import io.github.stream29.langchain4kt.core.ChatApiProvider
import io.github.stream29.langchain4kt.core.asRespondent
import io.github.stream29.langchain4kt.utils.MetapromptRespondent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class MetapromptTest {
    @Test
    fun qianfanTest() {
        testMetapromptWith(qianfanApiProvider)
    }

    @Test
    fun geminiTest() {
        testMetapromptWith(geminiApiProvider)
    }
}

fun testMetapromptWith(apiProvider: ChatApiProvider<*>) {
    val respondent = MetapromptRespondent(
        baseRespondent = apiProvider.asRespondent()
    ) { prompt ->
        """
                    $prompt
                    ##### 以上为输入 #####
                    
                    你是一个撰写GPT提示词的专家，你需要生成一段提示词，来引导GPT进行回复。
                    请写一段提示词，来引导一个语言模型回复输入的内容。
                    这段提示词应当满足以下条件：
                    阐述问题的背景，阐述输入的意图，引导模型回答问题，并且指出回答应当满足什么要求。
                    不要输出除了提示词以外的任何内容。
                    """.trimIndent()
    }
    runBlocking {
        launch {
            respondent.chat("一个初学者应该如何入门微积分呢？")
        }
        launch {
            respondent.chat("请介绍中国高考作文的标准格式。")
        }
    }
}