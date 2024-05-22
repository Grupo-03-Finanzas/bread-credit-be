import subprocess

def check_container_exists(container_name):
    try:
        result = subprocess.run(['docker', 'ps', '-a', '-f', f'name={container_name}', '--format', '{{.Names}}'],
                                stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        return container_name in result.stdout
    except subprocess.CalledProcessError as e:
        print(f"Error checking if container exists: {e}")
        return False

def remove_container(container_name):
    try:
        subprocess.run(['docker', 'rm', '-f', container_name], check=True)
        print(f"Container {container_name} removed successfully.")
    except subprocess.CalledProcessError as e:
        print(f"Error removing container: {e}")

def main():
    container_name = "bread-credit-database"

    if check_container_exists(container_name):
        user_input = input(f"Container {container_name} exists. Do you want to remove it? (y/n): ")
        if user_input.lower() == 'y':
            remove_container(container_name)
        else:
            print("Container was not removed.")
    else:
        print(f"Container {container_name} does not exist.")

if __name__ == "__main__":
    main()
