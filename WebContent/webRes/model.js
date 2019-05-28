function Book(title, author, genre)
{
	this.title = title;
	this.author = author;
	this.genre = genre;
	
	this.match = function(word)
	{
		return  matchingChars(this.title, word) > -1 ||
				matchingChars(this.author, word) > -1 ||
				matchingChars(this.genre, word) > -1;
	}
}
 
function matchingChars(a, b)
{
	a = a.toLowerCase();
	b = b.toLowerCase();
	return a.indexOf(b);
}
 

function makeBook(b)
{
	return new Book(b.children[0].innerHTML,
					b.children[1].innerHTML,
					b.children[2].innerHTML);
}