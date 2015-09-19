This is Joseph Lin's first web app. 
The goal of the web app was to utilize different technologies and see the full servlet lifecycle while implementing a way to view all of the database information.
The easiest way to view this is to run this servlet on your server. This will only work on my computer as it attempts to access my database on my computer. Eventually, I may try to change this,
but not for now.

What I learned:

I learned about JSP and the GET/POST nature of servlets. 
I learned about statelessness, and batching of data. 
I learned a way to query a SQL database and retrieve information.
I learned how to use servlets.
I learned request/responses with servlets.

What I would do if I were to start this project all over again:

Create a new class called Session, which would save all of the parameter's data. This can allow me to create a unique identifier for each individual accessing this page. This also allows me to properly save settings for each user. 
-Use Cookies (This allows me to have a direct unique example.

I would redo the query/batch cycle. It currently queries on every click to populate it. There's probably a simple fix to do this, but, when I look at it now, it's probably just to rip it all out and start that process all over again.


Stateless:
Currently, the backend has a state that changes according to the user requests. What needs to happen instead when we pass information
back to the frontend we should instead pass back the "next" and the previous"

Consider reducing down the get request to 2 parameters "Page Number" and "# of Entries".

Those two are the only things we truly need. In order to move around, we should change the buttons to return 
a specific value

Start should equal 0
Previous should equal Current_page_num - 1
Next should equal Current_page_num + 1
End should equal ${}