firebase.initializeApp({
    apiKey: "AIzaSyCVXKrg2DJsKg8k2zXi7yf0EaR1NCZeDKE",
    authDomain: "flowershop-a8b0c.firebaseapp.com",
    projectId: "flowershop-a8b0c",
    storageBucket: "flowershop-a8b0c.appspot.com",
    messagingSenderId: "97871611471",
    appId: "1:97871611471:web:79776792d32efabbaa4c3a",
    measurementId: "G-99S5X9JCX0"
});

const messaging = firebase.messaging();

messaging.requestPermission();

messaging.getToken(messaging, { vapidKey: 'AIzaSyCVXKrg2DJsKg8k2zXi7yf0EaR1NCZeDKE' }).then((currentToken) => {
    if (currentToken) {
        tokenFcm = currentToken;
        console.log("token you: "+currentToken)
        capNhatToken(currentToken);
    } else {
        console.log('No registration token available. Request permission to generate one.');
    }
}).catch((err) => {messaging.onMessage(function(payload) {
    alert("Foreground message fired!")
    console.log("Message received. ", payload);
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };

    self.registration.showNotification(notificationTitle,
        notificationOptions);
});
    console.log('An error occurred while retrieving token. ', err);
});

console.log(messaging)
// sử lý khi có tin nhắn đến




async function capNhatToken(token){
    var url = 'http://localhost:80/api/notification/update-token?token='+token;
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    });
}