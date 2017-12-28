#!/bin/bash

function _downCompose () {
    docker-compose down
}
function _deleteDockerImage () {
    echo "Deleting last docker image..."
    docker rmi java-ms-template:sql-db
}

function _buildDockerImage () {
    echo "Building docker image..."
    gradle buildDockerImage
}

function _upCompose () {
    echo "Docker componse uping!"
    docker-compose up
}

time(_downCompose)
time(_deleteDockerImage)
time(_buildDockerImage)
time(_upCompose)



