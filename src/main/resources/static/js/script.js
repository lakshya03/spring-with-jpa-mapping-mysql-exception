function myFunction() {
var name = document.getElementById("querytest").value;
if (name == '') {
alert("Please Fill  Fields");
} else {
// AJAX code to submit form.
$.ajax({
type: "POST",
url: "http://localhost:8080/rest/userscontact/check",
data: name,
cache: false,
success: function(html) {
alert(html);
}
});
}
return false;
}