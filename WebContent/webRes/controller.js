$(document).ready(function()
{
	getJSON();
	$ascending = false;
	$oldVal = "title";
	$('#filtro').keyup(function(event) { updateApp('F', this.value); });
	$("#radtitle, #radauthor, #radgenre").click(function(event) { updateApp('O', this.value); });
	$('.submitButton').click(function(event) { $.post('Home', $(this).parent().serialize(), refreshPage()); });
});

function updateApp(val, f)
{
	if(val == 'F')	filterBooks(f);
	if(val == 'O')	orderBooks(f);
}

function filterBooks(filter)
{
	var bookTable = document.getElementById('bookTable'),
		rows = bookTable.getElementsByTagName('tr');
	for(var i=1; i < rows.length; i++)
		checkFilter(rows, i, filter);
}

function checkFilter(rows, i, filter)
{
	var matches = !filter || filter == '' || makeBook(rows[i]).match(filter);
	if(matches) $(rows[i]).show();
	else $(rows[i]).hide();
}

function orderBooks(newVal)
{
	if($oldVal != newVal)
		$ascending = true;
	uncheck_check(newVal);
	$('#bookTable').find('tr:not(".heads")').sort(function(a,b) {
		return sortTable(a,b);
	}).appendTo($('#bookTable'));
	$ascending = $ascending ? false : true;
}

function sortTable(a,b)
{
	if($ascending)
		return td2sort(a) > td2sort(b) ? 1 : td2sort(a) < td2sort(b) ? -1 : 0;
	else
		return td2sort(a) > td2sort(b) ? -1 : td2sort(a) < td2sort(b) ? 1 : 0;
	return 0;
}

function uncheck_check(v)
{
	var currId = "#rad" + getRadioVal(), newId = "#rad" + v;
	$(currId).prop("checked", false); 	$(newId).prop("checked", true);
	$oldVal = v;
}
function getRadioId() { return {'title' : 0 , 'author' : 1, 'genre' : 2}[getRadioVal()]; }
function getRadioVal() { return $("input:radio:checked").val(); }
function refreshPage() { window.location.replace('home.jsp'); }
function td2sort(x) { return x.children[getRadioId()].innerHTML.toLowerCase(); }


updateApp();

