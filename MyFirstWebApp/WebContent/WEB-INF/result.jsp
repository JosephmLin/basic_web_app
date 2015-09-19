<%@page import="herodigital.servlet.MyServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What's in my Database?</title>

</head>
<body>
	<h3>
		Today is
		<%=new java.util.Date()%>. Have a nice day!
	</h3>
	
	<h3>
		Current Page #: ${current_page_number}
		<br>
		Number of Entries Per Page: ${page_size}
	</h3>
	
	<br/>
	<form action="MyServlet" method="get">
		<!-- There should be 7 links, though some may be hidden -->
		<!-- I.e. 1, 2 ... 6, 7, 8 ... 10, 11 -->
		
		<a id="link1" href="#"></a>
		<a id="link2" href="#"></a>
		<h1 id="elipse1">...</h1>
		<a id="link_current-1" href="#"></a>
		<a id="link_current" href="#"></a>
		<a id="link_current+1" href="#"></a>
		<h1 id="elipse1">...</h1>
		<a id="link_last-1" href="#"></a>
		<a id="link_last" href="#"></a>
		<br/>
		<br/>
		<br/>
		<table border="1">
			${event_info}
		</table>
		<h3>How many entries per page?</h3>
		<input id="page_size_text" type="text" name="entries_per_page" value = "${page_size}" }> <br>
		<input id="current_page_number" type="hidden" name="page_number" value="${current_page_number}" readonly>
		<script>
			var link_values = [];
			var link1 = document.getElementById("link1");
			link1 = defineLink(1,link1, link_values);
			
			var link2 = document.getElementById("link2");
			link2 = defineLink(2, link2, link_values );
			
			var link_cur_min1 = document.getElementById("link_current-1");
			link_cur_min1 = defineLink(${current_page_number} - 1, link_cur_min1 , link_values);
			
			var link_cur = document.getElementById("link_current");
			link_cur = defineLink(${current_page_number}, link_cur, link_values);
			
			var link_cur_plus1 = document.getElementById("link_current+1");
			link_cur_plus1 = defineLink(${current_page_number} + 1, link_cur_plus1, link_values);
			
			var link_last_min1= document.getElementById("link_last-1");
			link_last_min1 = defineLink(${page_count} - 1, link_last_min1, link_values);
			
			var link_last= document.getElementById("link_last");
			link_last = defineLink(${page_count}, link_last, link_values);
			function showLink(page_val, link_values)
			{
				
				var page_count = ${page_count};
				if (page_val > page_count || page_val <= 0)
				{
					return "none";
				}
				return "initial";
			}
			
			function defineLink(page_number, link, link_values)
			{
				link.innerHTML = page_number;
				var link_string = "?page_number="+page_number+"&entries_per_page=" + document.getElementById("page_size_text").value;
				link.href=link_string;
				link.style.display = showLink(page_number, link_values);
				return link;
			}
			
			
		</script>
	</form>
	<br />
</body>
</html>