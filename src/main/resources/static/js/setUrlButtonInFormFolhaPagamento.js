

//let form = document.querySelector('.form-folhaPagamento')
let button = document.querySelector('.add-contracheque')

let defaultURL = button.getAttribute('href')

function setUrl(selected){
 if(selected != 0){
   let setor = document.getElementById('inputSetor')
   setor.classList.add('is-valid')
   setor.classList.remove('is-invalid')
 }
 button.href = defaultURL  + selected 
}
