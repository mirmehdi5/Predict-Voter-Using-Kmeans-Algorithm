function myFunction(){
	console.log("Success");
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vote-rs/rest/getjson',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
        	document.getElementById('aboutUsText').innerHTML = response.key;
        	document.getElementById('myModal').style.display = "block";
        },
        error: function(error) {
            console.log(error);
        }
    });
}

window.onclick = function(event) {
	  if (event.target == document.getElementById('myModal')) {
		  document.getElementById('myModal').style.display = "none";
	  }
	}
