### Proof-of-concept project for refresh problem in IDEA

Load this code into IDEA.
Then run "ServerKt".

In your browser, open `localhost:8000`. There are three links
you can click around to. Those files have content that's served from
- a resource file in `:server` (local.htm)
- a resource file in `:content` (content.htm)
- returned by kotlin code in `:content` (SimpleServlet.kt)

To trigger the problem, open all those three files in the editor and edit them 
(e.g., copy "MODIFIED!" into them).
Now click the "rerun" icon for ServerKt in IDEA and in the popup
click "Stop and Rerun".

If you now click around in the browser, the URLs for the local resource and the
kotlin code will reflect the modifications, but the change in `content.htm`
is not updated.

Only by rebuilding the whole project (Ctrl-F9) and then restarting the server
you can see the change you made.

