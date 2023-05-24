package com.drinkz.screens

import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.drinkz.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.getDescription
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

@Composable
fun DrinkARScreen() {
//    var selectedItem by remember { mutableStateOf(0) }
    lateinit var sceneView: ArSceneView
    lateinit var addModelButton: ExtendedFloatingActionButton
    lateinit var placeModelButton: ExtendedFloatingActionButton
    lateinit var statusText: TextView
    var modelNode: ArModelNode? = null
//    val fragmentManager = (LocalContext.current as Activity).supportFragmentManager
//    val fragment = ARFragment()

    AndroidView(
        modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
        factory = { context ->
            // Creates view
            LayoutInflater.from(context).inflate(
                R.layout.fragment_ar, FrameLayout(context), false
            )
        }
    ) { view ->
        // View's been inflated or state read in this block has been updated
        // Add logic here if necessary
        statusText = view.findViewById(R.id.statusText)

        addModelButton =
        view.findViewById<ExtendedFloatingActionButton>(R.id.placeModelButton).apply {
            setOnClickListener {
                addModelButton.isVisible = false
                modelNode?.takeIf { !it.isAnchored }?.let {
                    sceneView.removeChild(it)
                    it.destroy()
                }
                modelNode = ArModelNode(
                    placementMode = PlacementMode.BEST_AVAILABLE,
                    hitPosition = Position(0.0f, 0.0f, -2.0f),
                    followHitPosition = true,
                    instantAnchor = false
                ).apply {
                    loadModelGlbAsync(
                        glbFileLocation = "models/test_lattina_birra.glb",
                        autoAnimate = true,
                        scaleToUnits = 0.1f,
                        centerOrigin = Position(x = 0.0f, y = -1.0f, z = 0.0f),
                        onError = { exception -> Log.e("Filament: ", exception.message!!) },
                        onLoaded = { Log.d("Filament: ", "entrato in onLoaded") }
                    )
                    sceneView.planeRenderer.isVisible = true
                    onAnchorChanged = { anchor ->
                        placeModelButton.isGone = anchor != null
                    }
                    onHitResult = { node, _ ->
                        placeModelButton.isGone = !node.isTracking
                    }
                }
                modelNode!!.isEditable = false
                sceneView.addChild(modelNode!!)
                sceneView.selectedNode = modelNode
                placeModelButton.isVisible = true
            }
        }

        placeModelButton =
        view.findViewById<ExtendedFloatingActionButton>(R.id.anchorModelButton).apply {
            setOnClickListener {
                modelNode?.anchor()
                placeModelButton.isVisible = false
                sceneView.planeRenderer.isVisible = false
            }
        }

        sceneView = view.findViewById<ArSceneView>(R.id.sceneView).apply {
            onArTrackingFailureChanged = { reason ->
                placeModelButton.isVisible = false
                addModelButton.isClickable = false
                statusText.text = reason?.getDescription(context)
                statusText.isGone = reason == null
                addModelButton.isClickable = statusText.isGone
            }
        }


//        modelNode?.onMoveBegin()



//            sceneView.cloudAnchorEnabled = true
//            // Host/Record a Cloud Anchor
//            modelNode.onAnchorChanged = { anchor1: Anchor? ->
//                if(anchor1 != null) {
//                    modelNode.hostCloudAnchor { anchor2: Anchor, success2: Boolean ->
//                        if (success2) {
//                            // Save the hosted Cloud Anchor Id
//                            val cloudAnchorId = anchor2.cloudAnchorId
//                            // Resolve/Restore the Cloud Anchor
//                            modelNode.resolveCloudAnchor(cloudAnchorId) { anchor3: Anchor, success3: Boolean ->
//                                if (success3) {
//                                    modelNode.isVisible = true
//                                }
//                            }
//                        }
//                    }
//                }
//            }


        // As selectedItem is read here, AndroidView will recompose
        // whenever the state changes
        // Example of Compose -> View communication
//            view.selectedItem = selectedItem
    }
}



