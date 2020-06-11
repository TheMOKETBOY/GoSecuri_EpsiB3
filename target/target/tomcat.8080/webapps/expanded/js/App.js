let videoWidth, videoHeight;

// whether streaming video from the camera.
let streaming = false;

let video = document.getElementById('video');
let canvasOutput = document.getElementById('canvasOutput');
let canvasOutputCtx = canvasOutput.getContext('2d');
let  faceOutput = document.getElementById('faceOutput');
let labelInput = document.getElementById('labelInput');
let stream = null;

let detectFace = document.getElementById('face');
let savemode = document.getElementById('savemode');
let automode = document.getElementById('automode');
let btncapture = document.getElementById('btnCapture');
btncapture.addEventListener("click",send_visage);
function send_visage() {
    let visage = faceOutput.toDataURL();

    let labelTxt = labelInput.value;

    console.log(visage)

    if (labelTxt !== ""){



    $.ajax({
        type: "POST",
        url: "/recofacial_app01_war_exploded/authvisage",
        data: {
            img: visage,
            label:labelTxt,
            issavemode:savemode.checked,
        },
        success: function(data){
            console.log(data);
            $("#outputpredict").text(data);
        },
        error: function(error){
            console.log("Error:");
            console.log(error);
        }
    });
}

}
function startCamera() {
    if (streaming) return;
    navigator.mediaDevices.getUserMedia({video: true, audio: false})
        .then(function(s) {
            stream = s;
            video.srcObject = s;
            video.play();
        })
        .catch(function(err) {
            console.log("An error occured! " + err);
        });

    video.addEventListener("canplay", function(ev){
        if (!streaming) {
            videoWidth = video.videoWidth;
            videoHeight = video.videoHeight;
            video.setAttribute("width", videoWidth);
            video.setAttribute("height", videoHeight);
            canvasOutput.width = videoWidth;
            canvasOutput.height = videoHeight;
            streaming = true;
        }
        startVideoProcessing();
    }, false);
}

let faceClassifier = null;
let eyeClassifier = null;

let src = null;
let dstC1 = null;
let dstC3 = null;
let dstC4 = null;

let canvasInput = null;
let canvasInputCtx = null;

let canvasBuffer = null;
let canvasBufferCtx = null;

function startVideoProcessing() {
    if (!streaming) { console.warn("Please startup your webcam"); return; }
    stopVideoProcessing();
    canvasInput = document.createElement('canvas');
    canvasInput.width = videoWidth;
    canvasInput.height = videoHeight;
    canvasInputCtx = canvasInput.getContext('2d');

    canvasBuffer = document.createElement('canvas');
    canvasBuffer.width = videoWidth;
    canvasBuffer.height = videoHeight;
    canvasBufferCtx = canvasBuffer.getContext('2d');

    srcMat = new cv.Mat(videoHeight, videoWidth, cv.CV_8UC4);
    grayMat = new cv.Mat(videoHeight, videoWidth, cv.CV_8UC1);

    faceClassifier = new cv.CascadeClassifier();
    faceClassifier.load('haarcascade_frontalface_default.xml');

    eyeClassifier = new cv.CascadeClassifier();
    eyeClassifier.load('haarcascade_eye.xml');

    requestAnimationFrame(processVideo);
}

function processVideo() {
    stats.begin();
    canvasInputCtx.drawImage(video, 0, 0, videoWidth, videoHeight);
    let imageData = canvasInputCtx.getImageData(0, 0, videoWidth, videoHeight);
    srcMat.data.set(imageData.data);
    cv.cvtColor(srcMat, grayMat, cv.COLOR_RGBA2GRAY);
    let faces = [];
    let eyes = [];
    let size;
    if (detectFace.checked) {
        let faceVect = new cv.RectVector();
        let faceMat = new cv.Mat();
        if (false) {
            cv.pyrDown(grayMat, faceMat);
            size = faceMat.size();
        } else {
            cv.pyrDown(grayMat, faceMat);
            cv.pyrDown(faceMat, faceMat);
            size = faceMat.size();
        }
        let msize = new cv.Size(50, 50);
        faceClassifier.detectMultiScale(faceMat, faceVect,1.1, 3,0, msize);
        for (let i = 0; i < faceVect.size(); i++) {
            let face = faceVect.get(i);


            faces.push(new cv.Rect(face.x, face.y, face.width, face.height));

        }
        faceMat.delete();
        faceVect.delete();
    } else {
        if (false) {
            let eyeVect = new cv.RectVector();
            let eyeMat = new cv.Mat();
            cv.pyrDown(grayMat, eyeMat);
            size = eyeMat.size();
            eyeClassifier.detectMultiScale(eyeMat, eyeVect);
            for (let i = 0; i < eyeVect.size(); i++) {
                let eye = eyeVect.get(i);
                eyes.push(new cv.Rect(eye.x, eye.y, eye.width, eye.height));
            }
            eyeMat.delete();
            eyeVect.delete();
        }
    }
    canvasOutputCtx.drawImage(canvasInput, 0, 0, videoWidth, videoHeight);
    drawResults(canvasOutputCtx, faces, 'red', size);
    drawResults(canvasOutputCtx, eyes, 'yellow', size);
    stats.end();
    requestAnimationFrame(processVideo);
}

function drawResults(ctx, results, color, size) {
    for (let i = 0; i < results.length; ++i) {
        let rect = results[i];
        let xRatio = videoWidth/size.width;
        let yRatio = videoHeight/size.height;
        ctx.lineWidth = 3;
        ctx.strokeStyle = color;
        let v = new cv.Mat();
        v = grayMat.roi(new cv.Rect(rect.x*xRatio, rect.y*yRatio, rect.width*xRatio, rect.height*yRatio)) ;
        let dsize = new cv.Size(50, 50);
        // You can try more different parameters
        cv.resize(v, v, dsize, 0, 0, cv.INTER_AREA);
        cv.imshow("faceOutput", v);
        ctx.strokeRect(rect.x*xRatio, rect.y*yRatio, rect.width*xRatio, rect.height*yRatio);
        if(automode.checked){
            send_visage();
        }
    }
}

function stopVideoProcessing() {
    if (src != null && !src.isDeleted()) src.delete();
    if (dstC1 != null && !dstC1.isDeleted()) dstC1.delete();
    if (dstC3 != null && !dstC3.isDeleted()) dstC3.delete();
    if (dstC4 != null && !dstC4.isDeleted()) dstC4.delete();
}

function stopCamera() {
    if (!streaming) return;
    stopVideoProcessing();
    document.getElementById("canvasOutput").getContext("2d").clearRect(0, 0, width, height);
    video.pause();
    video.srcObject=null;
    stream.getVideoTracks()[0].stop();
    streaming = false;
}

function initUI() {
    stats = new Stats();
    stats.showPanel(0);
    document.getElementById('container').appendChild(stats.dom);
}

function opencvIsReady() {
    console.log('OpenCV.js is ready');
    initUI();
    startCamera();
}