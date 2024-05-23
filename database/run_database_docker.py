import subprocess

def check_container_exists(container_name):
    try:
        result = subprocess.run(['docker', 'ps', '-a', '-f', f'name={container_name}', '--format', '{{.Names}}'],
                                stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        return container_name in result.stdout
    except subprocess.CalledProcessError as e:
        print(f"Error checking if container exists: {e}")
        return False

def check_container_running(container_name):
    try:
        result = subprocess.run(['docker', 'ps', '-f', f'name={container_name}', '--format', '{{.Names}}'],
                                stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        return container_name in result.stdout
    except subprocess.CalledProcessError as e:
        print(f"Error checking if container is running: {e}")
        return False

def start_container(container_name):
    try:
        subprocess.run(['docker', 'start', container_name], check=True)
        print(f"Container {container_name} started successfully.")
    except subprocess.CalledProcessError as e:
        print(f"Error starting container: {e}")

def remove_container(container_name):
    try:
        subprocess.run(['docker', 'rm', '-f', container_name], check=True)
        print(f"Container {container_name} removed successfully.")
    except subprocess.CalledProcessError as e:
        print(f"Error removing container: {e}")

def create_container(container_name, user, password, database):
    try:
        subprocess.run([
            'docker', 'run', '--name', container_name, '-p', '5432:5432',
            '-e', f'POSTGRES_USER={user}',
            '-e', f'POSTGRES_PASSWORD={password}',
            '-e', f'POSTGRES_DB={database}',
            '-d', 'postgres:latest'
        ], check=True)
        print(f"Container {container_name} created successfully.")
    except subprocess.CalledProcessError as e:
        print(f"Error creating container: {e}")

def main():
    container_name = "bread-credit-database"
    user = "PoliteBear"
    password = "BraveVolunteer365"
    database = "breaddatabase"

    if check_container_exists(container_name):
        if check_container_running(container_name):
            print(f"Container {container_name} already exists and is running.")
            user_input = input(f"Do you want to remove it and create a new one? (y/n): ")
            if user_input.lower() == 'y':
                remove_container(container_name)
                create_container(container_name, user, password, database)
            else:
                print("No action was taken.")
        else:
            print(f"Container {container_name} already exists but is not running.")
            user_input = input(f"Do you want to run it instead of creating a new one? (y/n): ")
            if user_input.lower() == 'y':
                start_container(container_name)
            else:
                user_input = input(f"Do you want to remove it and create a new one? (y/n): ")
                if user_input.lower() == 'y':
                    remove_container(container_name)
                    create_container(container_name, user, password, database)
                else:
                    print("No action was taken.")
    else:
        create_container(container_name, user, password, database)


if __name__ == "__main__":
    main()