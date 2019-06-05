import tensorflow as tf
from tensorflow.python.saved_model import tag_constants
import argparse
import os

parser = argparse.ArgumentParser()
parser.add_argument('-i', '--input-file', help='Description', required=True)
parser.add_argument('-o', '--output-path', help='Description', required=False, default=None)
parser.add_argument('-p', '--graph-prefix', help='Description', required=False, default="tf4j/")

args = parser.parse_args()

load_graph_path = args.input_file
save_graph_path = args.output_path
prefix = args.graph_prefix

if not save_graph_path:
    save_graph_path = os.path.basename(load_graph_path).replace(".", "_").replace(" ", "_")

if os.path.exists(save_graph_path):
    save_graph_path += "/" + os.path.basename(load_graph_path).replace(".", "_").replace(" ", "_")

builder = tf.saved_model.builder.SavedModelBuilder(save_graph_path)

with tf.Session() as sess:
    with sess.graph.as_default():
        od_graph_def = tf.GraphDef()
        with tf.gfile.GFile(load_graph_path, 'rb') as fid:
            serialized_graph = fid.read()
            od_graph_def.ParseFromString(serialized_graph)
            tf.import_graph_def(od_graph_def, name=prefix)
    builder.add_meta_graph_and_variables(sess, [tag_constants.SERVING])

builder.save()
