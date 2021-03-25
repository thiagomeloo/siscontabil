document.querySelector('.alert').addEventListener('closed.bs.alert', function () {
  var newURL = location.href.split("?")[0];
    window.history.pushState('object', document.title, newURL)
})
