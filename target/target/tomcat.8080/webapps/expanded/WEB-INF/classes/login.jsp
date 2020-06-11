<%--
  Created by IntelliJ IDEA.
  User: tatel
  Date: 01/11/2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Projet Mspr</title>
  </head>
  <body>
  <div id="container">
    <canvas class="center-block" id="canvasOutput" width=320 height=240></canvas>
    <canvas class="center-block" id="faceOutput" width="150" height="150"></canvas>
  </div>

  <div class="text-center">
    <input type="text" id="labelInput">
    <button id="btnCapture">Capture visage</button>
    <input type="checkbox" id="face" name="classifier" value="face" checked>
    <label for="face">face</label>
    <input type="checkbox" id="savemode" name="cascade" value="eye">
    <label for="savemode">save mode</label>
    <input type="checkbox" id="automode" name="cascade" value="eye">
    <label for="savemode">save mode</label>
    <h3 id="outputpredict"></h3>

  </div>
  <div class="invisible" style="display: none">
    <video id="video" class="hidden">Your browser does not support the video tag.</video>
  </div>
  </div>
  </body>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/App.js"> </script>
  <script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
  <script src="https://threejs.org/examples/js/libs/stats.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/dat-gui/0.6.5/dat.gui.min.js"></script>
  <script>
    var Module = {
      wasmBinaryFile: 'https://huningxin.github.io/opencv.js/build/wasm/opencv_js.wasm',
      preRun: [function() {
        Module.FS_createPreloadedFile('/', 'haarcascade_eye.xml', 'https://raw.githubusercontent.com/opencv/opencv/master/data/haarcascades/haarcascade_eye.xml', true, false);
        Module.FS_createPreloadedFile('/', 'haarcascade_frontalface_default.xml', 'https://raw.githubusercontent.com/opencv/opencv/master/data/haarcascades/haarcascade_frontalface_default.xml', true, false);
        Module.FS_createPreloadedFile('/', 'haarcascade_profileface.xml', 'https://raw.githubusercontent.com/opencv/opencv/master/data/haarcascades/haarcascade_profileface.xml', true, false);
      }],
      _main: function() {opencvIsReady();}
    };
  </script>
  <script async src="https://huningxin.github.io/opencv.js/build/wasm/opencv.js"></script>

  </body>
</html>