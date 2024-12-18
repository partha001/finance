import io
from base64 import encodebytes

import PIL.Image
from flask.views import MethodView
from flask_smorest import Blueprint, abort

from services.AssetService import getAssetChart

blp = Blueprint("assets",__name__,description="operations on assets")

@blp.route("/assets")
class Assets(MethodView):

    def get(self):
        test = getAssetChart()
        return test,200

@blp.route("/assets/chart/assetsVsTime")
class AssetChartOperations(MethodView):

    def get(self):
        try:
            pil_img = PIL.Image.open("test.png", "r") # reads the PIL image
            byte_arr = io.BytesIO()
            pil_img.save(byte_arr, format='PNG') # convert the PIL image to byte array
            encoded_img = encodebytes(byte_arr.getvalue()).decode('ascii') # encode as base64
            return encoded_img
        except KeyError:
            abort(404, message="item with itemId =  not found")





