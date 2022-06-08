function search(mailBox) {
    let user = parseJwt(window.localStorage.getItem("token"));
    console.log(user);
    let hashtag = (mailBox == 'inbox') ?
        document.getElementById('searchTag').value :
        document.getElementById('searchTagSent').value;
    fetch("http://localhost:2022/messages/hashtag/" + hashtag + "?id=" + user.id, {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        }
    }).then(async res => {
        let messages = await res.text();
        console.log(messages);
        messages = messages.split("###");
        let table = (mailBox == 'inbox') ?
            document.getElementById('inboxTable') :
            document.getElementById('outboxTable');
        table.querySelector('thead').querySelector('tr').querySelector('th').innerText = "Author";
        table.querySelector('tbody').innerHTML = "";
        for (let i = 0; i < messages.length; ++i) {
            const newRow = document.createElement("tr");
            const newColumn1 =document.createElement("td");
            newColumn1.innerText = JSON.parse(messages[i]).author;
            const newColumn2 =document.createElement("td");
            newColumn2.innerText = JSON.parse(messages[i]).message;
            newRow.append(newColumn1);
            newRow.append(newColumn2);
            table.querySelector('tbody').appendChild(newRow);
        }
    }).catch(err => {
        console.log(err);
    })
}
