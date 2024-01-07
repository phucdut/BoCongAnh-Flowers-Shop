async function findAllProduct() {
  $('#example').DataTable().destroy();
  var url = 'http://103.153.72.198:8080/staff/all-product';
  const response = await fetch(url, {
    method: 'GET'
  });
  var list = await response.json();
  console.log(list)
  var main = '';
  for (i = 0; i < list.length; i++) {
    main += `<tr>
            <td>${list[i].id}</td>
            <td>${list[i].name}</td>
            <td>${formatmoney(list[i].originalPrice)}</td>
            <td>${formatmoney(list[i].price)}</td>
            <td>${list[i].overall_rating}</td>
            <td>${list[i].discount}</td>
            <td class="sticky-col">
                    <a onclick="loadAProduct(${list[i].id})" data-bs-toggle="modal" data-bs-target="#viewdetail"><i class="fa fa-eye iconaction"></i></a>
                </td>
        </tr>`
  }
  document.getElementById("listProduct").innerHTML = main;
  $('#example').DataTable();
}


async function loadAProduct(id) {
  var url = 'http://103.153.72.198:8080/staff/all-product-by-id?id='+id;
  const response = await fetch(url, {
    method: 'GET',
    headers: new Headers({
    })
  });
  var obj = await response.json();
  var mainimg = ''
  if(obj.image1 != null && obj.image1 != ""){
    mainimg += `<div class="col-lg-2"><img src="/images/${obj.image1}" style="width: 100%;"></div>`
  }
  if(obj.image2 != null && obj.image2 != ""){
    mainimg += `<div class="col-lg-2"><img src="/images/${obj.image2}" style="width: 100%;"></div>`
  }
  if(obj.image3 != null && obj.image3 != ""){
    mainimg += `<div class="col-lg-2"><img src="/images/${obj.image3}" style="width: 100%;"></div>`
  }
  if(obj.image4 != null && obj.image4!= ""){
    mainimg += `<div class="col-lg-2"><img src="/images/${obj.image4}" style="width: 100%;"></div>`
  }
  if(obj.image5 != null && obj.image5!= ""){
    mainimg += `<div class="col-lg-2"><img src="/images/${obj.image5}" style="width: 100%;"></div>`
  }

  document.getElementById("listimgdetail").innerHTML = mainimg;
  document.getElementById("despro").innerHTML = obj.description;
  document.getElementById("details").innerHTML = obj.details;
  document.getElementById("delivery").innerHTML = obj.delivery;
  document.getElementById("subinfo").innerHTML = obj.sub_info;

  var listdm = '<div class="listtag">'
  for (j = 0; j < obj.categoryEntities.length; j++) {
    listdm += `<a href="" class="tagcauhoi">.${obj.categoryEntities[j].name}</a>`;
  }
  document.getElementById("listCategory").innerHTML = listdm

}


