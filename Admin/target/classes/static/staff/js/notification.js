async function findAllNotify() {
    // $('#example').DataTable().destroy();
    var url = 'http://103.153.72.198:8080/staff/all-notify';
    const response = await fetch(url, {
        method: 'GET'
    });
    var list = await response.json();
    var main = '';
    for (i = 0; i < list.length; i++) {
        var add = '';
        main += `<tr>
                  <td>${list[i].id}</td>
                  <td><img src="/images/${list[i].image}" style="width: 100px"></td>
                  <td>${list[i].title}</td>
                  <td>${list[i].content}</td>
                  <td>${list[i].type}</td>
              </tr>`
    }
    document.getElementById("listNotify").innerHTML = main;
    // $('#example').DataTable();
}
