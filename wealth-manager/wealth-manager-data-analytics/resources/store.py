import uuid
from flask import request
from flask.views import MethodView
from flask_smorest import Blueprint, abort
from db import stores
import services.AssetService

blp = Blueprint("stores",__name__,description="Operations on store")

@blp.route("/store")
class Store(MethodView):

    def get(self):
        return {"stores": list(stores.values())},200


    def post(self):
        request_data = request.get_json()  #reads the post-request payload to a dictionary
        storeId = uuid.uuid4().hex

        for store in stores:
            if (stores[store])["name"] == request_data["name"]:
                return "store = {} already exists".format(request_data["name"]), 200

        new_store = {"storeId": storeId, "name": request_data["name"], "items": []}
        stores[storeId] = new_store
        return new_store, 201


@blp.route("/store/<string:storeId>")
class StoreIdOperations(MethodView):

    def get(self,storeId):
        try:
            return stores[storeId]
        except KeyError:
            abort(404, message="store with storeId = {} not found".format(storeId))


    def delete(self,storeId):
        try:
            del stores[storeId]
            return {"message":"store with deleted successfully "},200
        except KeyError:
            abort(404, message="store with storeId = {} not found".format(storeId))