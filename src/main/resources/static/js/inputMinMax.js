function VerifieldMinMax(input){
  if(input.value != ""){
    if(parseInt(input.value) < parseInt(input.min)){
      input.value = input.min;
    }
    if(parseInt(input.value) > parseInt(input.max)){
      input.value = input.max;
    }
  }
}