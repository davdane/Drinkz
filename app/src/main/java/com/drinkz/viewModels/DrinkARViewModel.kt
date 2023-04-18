package com.drinkz.viewModels

import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.github.sceneview.ar.ArSceneView

class DrinkARViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
//    lateinit var arCoreSessionHelper: ARCoreSessionLifecycleHelper
//    lateinit var view: HelloArView
//    lateinit var renderer: ARRenderer
    lateinit var sceneView: ArSceneView
    lateinit var loadingView: View

//    val instantPlacementSettings = InstantPlacementSettings()
//    val depthSettings = DepthSettings()

//    fun onCreate(arSceneView: ArSceneView) {
//
//        // Setup ARCore session lifecycle helper and configuration.
//        arCoreSessionHelper = ARCoreSessionLifecycleHelper(activity)
//        // If Session creation or Session.resume() fails, display a message and log detailed
//        // information.
//        arCoreSessionHelper.exceptionCallback =
//            { exception ->
//                val message =
//                    when (exception) {
//                        is UnavailableUserDeclinedInstallationException ->
//                            "Please install Google Play Services for AR"
//                        is UnavailableApkTooOldException -> "Please update ARCore"
//                        is UnavailableSdkTooOldException -> "Please update this app"
//                        is UnavailableDeviceNotCompatibleException -> "this device does not support AR"
//                        is CameraNotAvailableException -> "Camera not available. Try restarting the app."
//                        else -> "Failed to create AR session: $exception"
//                    }
//                Log.e(MainActivity.TAG, "ARCore threw an exception: $message", exception)
////                arSceneView.showError(activity, message)
//            }
//
//        // Configure session features, including: Lighting Estimation, Depth mode, Instant Placement.
//        arCoreSessionHelper.beforeSessionResume = ::configureSession
//
//        activity.lifecycle.addObserver(arCoreSessionHelper)
//
////                // Set up the Hello AR renderer.
////                renderer = ARRenderer(activity)
////                activity.lifecycle.addObserver(renderer)
//
////         Set up Hello AR UI.
////                view = HelloArView(activity)
//        activity.lifecycle.addObserver(arSceneView)
////        setContentView(view.root)
//
////        // Sets up an example renderer using our HelloARRenderer.
////        SampleRender(view.surfaceView, renderer, assets)
//
////        depthSettings.onCreate(activity)
////        instantPlacementSettings.onCreate(activity)
//
//        val modelUri = Uri.parse("cup_saucer_set.usdz")
//        val renderableFuture = ModelRenderable.builder()
//            .setSource(activity, modelUri)
//            .build()
//        renderableFuture.thenAccept { renderable ->
//            val anchor = arCoreSessionHelper.session?.createAnchor(Pose.IDENTITY)
//            val anchorNode = AnchorNode(anchor)
//
//            val modelNode = TransformableNode(arSceneView.transformationSystem)
//            modelNode.setParent(anchorNode)
//            modelNode.renderable = renderable
//            arSceneView.scene.addChild(anchorNode)
//        }
//    }

//    fun configureSession(session: Session) {
//        session.configure(
//            session.config.apply {
//                lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
//
//                // Depth API is used if it is configured in Hello AR's settings.
//                depthMode =
//                    if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
//                        Config.DepthMode.AUTOMATIC
//                    } else {
//                        Config.DepthMode.DISABLED
//                    }
//
//                // Instant Placement is used if it is configured in Hello AR's settings.
//                instantPlacementMode =
//                    if (InstantPlacementSettings().isInstantPlacementEnabled) {
//                        Config.InstantPlacementMode.LOCAL_Y_UP
//                    } else {
//                        Config.InstantPlacementMode.DISABLED
//                    }
//            }
//        )
//    }
}
