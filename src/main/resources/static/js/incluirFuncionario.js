function incluirFuncionario(event, object){
  event.preventDefault()

  let selected = document.getElementById("inputSetor")

  console.log()

  if(selected.selectedIndex != 0){
    window.location = object.href
  }else{
    selected.classList.add('is-invalid')
    
  }


}