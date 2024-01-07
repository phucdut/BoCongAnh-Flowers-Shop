async function findAllCustomer() {
  // $('#example').DataTable().destroy();
  var url = 'http://103.153.72.198:8080/staff/all-customer';
  const response = await fetch(url, {
    method: 'GET'
  });
  var list = await response.json();
  var main = '';
  for (i = 0; i < list.length; i++) {
    var add = '';
    for(j=0; j<list[i].addresses.length; j++){
      add += list[i].addresses[j].street+", "+list[i].addresses[j].ward+", "+list[i].addresses[j].district+", "+list[i].addresses[j].city
    }
    main += `<tr>
                  <td>${list[i].id}</td>
                  <td><img src="/customer/${list[i].avatar}" style="width: 100px"></td>
                  <td>${add}</td>
                  <td>${list[i].email}</td>
                  <td>${list[i].fullName}</td>
                  <td>${list[i].phone}</td>
                  <td>${list[i].username}</td>
              </tr>`
  }
  document.getElementById("listCustomer").innerHTML = main;
  // $('#example').DataTable();
}
