import com.google.common.io.Resources
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

private fun getResource(name: String) = Resources.toString(Resources.getResource(name), Charsets.UTF_8)

fun main() {
    val knownUrls =
        mapOf("/local_resource.htm" to getResource("local.htm"),
              "/content_project_resource.htm" to getResource("content.htm"),
              "/content_project_code.htm" to SimpleServlet().content())

    val links =
        knownUrls.keys.map { "<a href=\"${it}\">${it}</a><br>" }.joinToString("<br>")

    embeddedServer(Netty, port = 8000) {
        intercept(ApplicationCallPipeline.Call) {
            val content = knownUrls[call.request.path()]
            if (content == null) {
                call.respondText(links, ContentType.Text.Html)
            } else {
                call.respondText(content + "<br>".repeat(5) + links, ContentType.Text.Html)
            }
        }
    }.start(wait = true)
}