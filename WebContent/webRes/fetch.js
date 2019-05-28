function getJSON()
{
	$.get('Home', function(responseJson) {
		if(responseJson != null)
			loadBooks(loadSelects(responseJson));
	});
}

function loadSelects(list)
{ 
	load(".genresSelect", list.pop());
	load(".titlesSelect", list.pop());
	return list;
}

function load(select, list)
{
	var string = "";
	$.each(list, function(key,val) {
		string += "<option value='"+val+"'>"+val+"</option>"
	});
	var options = $(string);
	$(select).html(options);
}

function loadBooks(list)
{
	$.each(list, function(key,value) {
		var row = $("<tr style='text-align: center'><td></td><td></td><td></td><td></td></tr>");
		for(var i=0; i<=3; i++)
			row.children().eq(i).text(value[["title","author","genre","path"][i]]);
		row.appendTo($("#bookTable"));
	});
}
