# Use the official Debian Bookworm image as the base
FROM debian:bookworm

# Set environment variables to avoid interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

# Update and install required packages, including Python and Java
RUN apt-get update && \
    apt-get install -y \
    python3 \
    python3-pip \
    openjdk-17-jdk \
    sudo \
    && apt-get clean

# Create a non-root user
ARG USERNAME=appuser
ARG USER_UID=1001
ARG USER_GID=$USER_UID

# Create the user with a home directory
RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \
    && echo "$USERNAME ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

# Switch to the non-root user
USER $USERNAME

# Set the working directory
WORKDIR /home/$USERNAME

# Set JAVA_HOME environment variable for the user
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Verify installations
RUN java -version && python3 --version

# Set a default command (optional)
CMD ["bash"]
